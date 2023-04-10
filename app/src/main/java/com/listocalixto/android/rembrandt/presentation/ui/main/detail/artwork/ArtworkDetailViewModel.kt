package com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.listocalixto.android.rembrandt.R
import com.listocalixto.android.rembrandt.core.Constants.ANIMATION_REFRESH_DURATION
import com.listocalixto.android.rembrandt.core.Constants.EMPTY
import com.listocalixto.android.rembrandt.domain.entity.Artwork
import com.listocalixto.android.rembrandt.domain.model.Translation
import com.listocalixto.android.rembrandt.domain.model.Translation.Exception.TargetLanguageNotAvailable
import com.listocalixto.android.rembrandt.domain.usecase.main.ArtworkDetailUseCases
import com.listocalixto.android.rembrandt.domain.utility.RecommendationType
import com.listocalixto.android.rembrandt.domain.utility.RecommendationType.SameArtist
import com.listocalixto.android.rembrandt.domain.utility.RecommendationType.SameArtworkType
import com.listocalixto.android.rembrandt.domain.utility.RecommendationType.SameCategory
import com.listocalixto.android.rembrandt.domain.utility.RecommendationType.SameGallery
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.ArtworkDetailFragment.Companion.ARTWORK_ID_DEFAULT_VALUE
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.ArtworkDetailFragment.Companion.ARTWORK_ID_KEY
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.ArtworkDetailFragment.Companion.MEMORY_CACHE_KEY_ID_KEY
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.ArtworkDetailUiEvent.ErrorMessageTriggered
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.ArtworkDetailUiEvent.OnChipFavorite
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.ArtworkDetailUiEvent.RefreshAnimationTriggered
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.ArtworkDetailUiEvent.SaveCurrentArtworkId
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.ArtworkDetailUiEvent.TranslateContent
import com.listocalixto.android.rembrandt.presentation.utility.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch

@HiltViewModel
class ArtworkDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useCases: ArtworkDetailUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(ArtworkDetailUiState())
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ArtworkDetailUiState()
    )
    private val viewModelDispatcher = viewModelScope.coroutineContext + Dispatchers.Main

    private var updateArtworkJob: Job? = null
    private var translateArtworkJob: Job? = null

    init {
        viewModelScope.launch(viewModelDispatcher) {
            val artworkIdFlow: Flow<Long>
            val memoryCacheKeyFlow: Flow<String>
            with(savedStateHandle) {
                artworkIdFlow = getStateFlow(ARTWORK_ID_KEY, ARTWORK_ID_DEFAULT_VALUE)
                memoryCacheKeyFlow = getStateFlow(MEMORY_CACHE_KEY_ID_KEY, EMPTY)
                artworkIdFlow.zip(memoryCacheKeyFlow) { artworkId, memoryCacheKey ->
                    ArtworkDetailFragmentArgs(artworkId, memoryCacheKey)
                }.collect { args ->
                    _uiState.update { it.copy(memoryCacheKey = args.memoryCacheKey) }
                    setupContentByArtworkId(args.artworkId)
                }
            }
        }
    }

    fun onEvent(event: ArtworkDetailUiEvent): Unit = when (event) {
        SaveCurrentArtworkId -> {
            savedStateHandle[ARTWORK_ID_KEY] =
                _uiState.value.artwork?.id ?: ARTWORK_ID_DEFAULT_VALUE
        }
        OnChipFavorite -> Unit.apply {
            if (updateArtworkJob != null) return@apply
            updateArtworkJob = viewModelScope.launch(viewModelDispatcher) {
                val currentArtwork = _uiState.value.artwork ?: run {
                    updateArtworkJob = null
                    return@launch
                }
                useCases.updateArtwork(currentArtwork.copy(isFavorite = !currentArtwork.isFavorite))
                updateArtworkJob = null
            }
        }
        TranslateContent -> Unit.apply {
            if (translateArtworkJob != null) return@apply
            val artwork = _uiState.value.artwork ?: return@apply
            val translation = artwork.translation
            if (translation == null) {
                translateArtworkJob = viewModelScope.launch(viewModelDispatcher) {
                    _uiState.update { it.copy(loadingTranslation = true) }
                    try {
                        val newTranslation = useCases.getTranslationByArtwork(artwork)
                        _uiState.update { it.copy(triggerRefreshAnimation = Unit) }
                        delay(ANIMATION_REFRESH_DURATION)
                        useCases.setTranslationByArtwork(artwork, newTranslation)
                    } catch (e: Translation.Exception) {
                        when (e) {
                            TargetLanguageNotAvailable -> {
                                _uiState.update {
                                    it.copy(
                                        errorMessage = UiText.StringResource(
                                            R.string.frag_artwork_detail_err_translation_not_available
                                        )
                                    )
                                }
                            }
                        }
                    } finally {
                        translateArtworkJob = null
                        _uiState.update { it.copy(loadingTranslation = false) }
                    }
                }
            } else {
                // Toggle translation.
                viewModelScope.launch {
                    _uiState.update { it.copy(triggerRefreshAnimation = Unit) }
                    delay(ANIMATION_REFRESH_DURATION)
                    _uiState.update { it.copy(translate = !it.translate) }
                }
            }
        }
        RefreshAnimationTriggered -> {
            _uiState.update { it.copy(triggerRefreshAnimation = null) }
        }
        ErrorMessageTriggered -> {
            _uiState.update { it.copy(errorMessage = null) }
        }
    }

    private suspend fun setupContentByArtworkId(id: Long) {
        useCases.observeArtworkWithManifest(id).catch {
        }.collect { artwork ->
            setupArtworkDetailScreen(artwork)
            if (recommendationsHaveNotBeenInitialized()) {
                fetchAndSetupRecommendedArtworks(artwork)
            }
        }
    }

    private suspend fun fetchAndSetupRecommendedArtworks(artwork: Artwork) {
        useCases.getRecommendedArtworksByArtwork(artwork).apply {
            setupRecommendedArtworks(artworksRecommended, recommendationTypes)
        }
    }

    private fun recommendationsHaveNotBeenInitialized(): Boolean {
        return _uiState.value.artworksRecommended == null
    }

    private fun setupRecommendedArtworks(
        artworksRecommended: List<Artwork>,
        recommendationTypes: List<RecommendationType>
    ) {
        if (artworksRecommended.size != recommendationTypes.size) return
        val recommendationsUiState = artworksRecommended.mapIndexed { index, artworkRecommended ->
            ArtworkRecommendedUiState(
                id = artworkRecommended.id,
                imageUrl = artworkRecommended.imageUrl,
                title = artworkRecommended.title,
                reasonItWasRecommended = when (recommendationTypes[index]) {
                    is SameArtist -> {
                        R.string.reason_it_was_recommended_same_artist
                    }
                    is SameArtworkType -> {
                        R.string.reason_it_was_recommended_same_artwork_type
                    }
                    is SameCategory -> {
                        R.string.reason_it_was_recommended_same_category
                    }
                    is SameGallery -> {
                        R.string.reason_it_was_recommended_same_gallery
                    }
                }
            )
        }
        _uiState.update { it.copy(artworksRecommended = recommendationsUiState) }
    }

    private fun setupArtworkDetailScreen(artwork: Artwork) {
        _uiState.update {
            it.copy(artwork = artwork)
        }
    }
}
