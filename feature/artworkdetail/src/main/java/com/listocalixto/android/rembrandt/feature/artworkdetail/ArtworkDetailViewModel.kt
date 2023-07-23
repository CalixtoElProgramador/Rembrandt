package com.listocalixto.android.rembrandt.feature.artworkdetail

import android.graphics.Color.TRANSPARENT
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.listocalixto.android.rembrandt.common.entities.Artwork
import com.listocalixto.android.rembrandt.common.entities.utility.RecommendationType
import com.listocalixto.android.rembrandt.common.entities.utility.RecommendationType.SameArtist
import com.listocalixto.android.rembrandt.common.entities.utility.RecommendationType.SameArtworkType
import com.listocalixto.android.rembrandt.common.entities.utility.RecommendationType.SameCategory
import com.listocalixto.android.rembrandt.common.entities.utility.RecommendationType.SameGallery
import com.listocalixto.android.rembrandt.core.domain.usecase.ObserveArtworkUserByIdUseCase
import com.listocalixto.android.rembrandt.core.domain.usecase.ToggleFavoriteArtworkByIdUseCase
import com.listocalixto.android.rembrandt.core.ui.states.ArtworkRecommendedUiState
import com.listocalixto.android.rembrandt.core.ui.utility.UiText
import com.listocalixto.android.rembrandt.feature.artworkdetail.ArtworkDetailFragment.Companion.AMBIENT_COLOR_KEY
import com.listocalixto.android.rembrandt.feature.artworkdetail.ArtworkDetailFragment.Companion.ARTWORK_ID_KEY
import com.listocalixto.android.rembrandt.feature.artworkdetail.ArtworkDetailFragment.Companion.MEMORY_CACHE_KEY
import com.listocalixto.android.rembrandt.feature.artworkdetail.ArtworkDetailFragment.Companion.SHOW_ENTER_ANIMATIONS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.listocalixto.android.rembrandt.core.ui.R as Rui

@HiltViewModel
class ArtworkDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    observeArtworkUserById: ObserveArtworkUserByIdUseCase,
    private val toggleFavoriteArtworkById: ToggleFavoriteArtworkByIdUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ArtworkDetailUiState())
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ArtworkDetailUiState(),
    )
    private val viewModelDispatcher = viewModelScope.coroutineContext + Dispatchers.Main

    private var updateArtworkJob: Job? = null
    private var translateArtworkJob: Job? = null

    private data class ArtworkDetailFragmentArgs(
        val artworkId: Long,
        val imageMemoryCacheKey: String?,
        val shouldShowEnterAnimations: Boolean,
        val imageAmbientColor: Int,
    )

    init {
        getNavigationArguments(savedStateHandle).run {
            setArgumentsInTheUiState(this)
            observeArtworkUserById(artworkId).catch { throwable ->
                _uiState.update {
                    it.copy(errorMessage = UiText.StringValue(throwable.message.orEmpty()))
                }
            }.onEach { artworkUser ->
                _uiState.update { it.copy(artworkUser = artworkUser) }
            }.launchIn(viewModelScope)
        }

        /*viewModelScope.launch(viewModelDispatcher) {
            with(savedStateHandle) {
                *//*artworkIdFlow = getStateFlow(ARTWORK_ID_KEY, ARTWORK_ID_DEFAULT_VALUE)
                memoryCacheKeyFlow = getStateFlow(MEMORY_CACHE_KEY_ID_KEY, EMPTY)
                displayInitialAnimations = getStateFlow(DISPLAY_INITIAL_ANIMATIONS_KEY, true)
                gradientColor = getStateFlow(GRADIENT_COLOR_KEY, Color.TRANSPARENT)
                artworkIdFlow.zip(memoryCacheKeyFlow) { artworkId, memoryCacheKey ->
                    ArtworkDetailFragmentArgs(
                        gradientColor = Color.TRANSPARENT,
                        artworkId = artworkId,
                        memoryCacheKey = memoryCacheKey,
                    )
                }.zip(displayInitialAnimations) { args, displayInitialAnimations ->
                    args.copy(displayInitialAnimations = displayInitialAnimations)
                }.zip(gradientColor) { args, gradientColor ->
                    args.copy(gradientColor = gradientColor)
                }.collect { args ->
                    _uiState.update {
                        it.copy(
                            memoryCacheKey = args.memoryCacheKey,
                            initialAnimationsDisplayed = !args.displayInitialAnimations,
                            gradientColor = args.gradientColor,
                        )
                    }
                    if (_uiState.value.initialAnimationsDisplayed.not()) {
                        displayInitialAnimations()
                    }
                    setupContentByArtworkId(args.artworkId)
                }*//*
            }
        }*/
    }

    private fun setArgumentsInTheUiState(args: ArtworkDetailFragmentArgs) {
        _uiState.update {
            it.copy(
                imageMemoryCacheKey = args.imageMemoryCacheKey,
                shouldShowEnterAnimations = args.shouldShowEnterAnimations,
                imageAmbientColor = args.imageAmbientColor,
                artworkId = args.artworkId,
            )
        }
    }

    fun onChipFavoriteClick() {
        val artworkId = _uiState.value.artworkId
        val isFavorite = _uiState.value.isFavorite
        viewModelScope.launch(viewModelDispatcher) {
            toggleFavoriteArtworkById(
                artworkId,
                isFavorite,
            )
        }
    }

    fun enterAnimationsShowed() {
        _uiState.update { it.copy(shouldShowEnterAnimations = false) }
    }

    private fun getNavigationArguments(savedStateHandle: SavedStateHandle): ArtworkDetailFragmentArgs {
        val artworkId: Long = savedStateHandle[ARTWORK_ID_KEY] ?: -1
        val imageMemoryCacheKey: String? = savedStateHandle[MEMORY_CACHE_KEY]
        val shouldShowEnterAnimations: Boolean = savedStateHandle[SHOW_ENTER_ANIMATIONS] ?: false
        val imageAmbientColor: Int = savedStateHandle[AMBIENT_COLOR_KEY] ?: TRANSPARENT
        return ArtworkDetailFragmentArgs(
            artworkId = artworkId,
            imageMemoryCacheKey = imageMemoryCacheKey,
            shouldShowEnterAnimations = shouldShowEnterAnimations,
            imageAmbientColor = imageAmbientColor,
        )
    }

    /*fun onEvent(event: ArtworkDetailUiEvent): Unit = when (event) {
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
                // useCases.updateArtwork(currentArtwork.copy(isFavorite = !currentArtwork.isFavorite))
                updateArtworkJob = null
            }
        }

        TranslateContent -> Unit.apply {
            if (translateArtworkJob != null) return@apply
            val artwork = _uiState.value.artwork ?: return@apply
//            val translation = artwork.translation
            *//*if (translation == null) {
                translateArtworkJob = viewModelScope.launch(viewModelDispatcher) {
                    _uiState.update { it.copy(loadingTranslation = true) }
                    try {
                        // val newTranslation = useCases.getTranslationByArtwork(artwork)
                        _uiState.update { it.copy(triggerRefreshAnimation = Unit) }
                        delay(ANIMATION_REFRESH_DURATION)
                        // useCases.setTranslationByArtwork(artwork, newTranslation)
                    } catch (e: Translation.Exception) {
                        when (e) {
                            TargetLanguageNotAvailable -> {
                                _uiState.update {
                                    it.copy(
                                        errorMessage = UiText.StringResource(
                                            R.string.frag_artwork_detail_err_translation_not_available,
                                        ),
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
            }*//*
        }

        *//*RefreshAnimationTriggered -> {
            _uiState.update { it.copy(triggerRefreshAnimation = null) }
        }

        ErrorMessageTriggered -> {
            _uiState.update { it.copy(errorMessage = null) }
        }

        InitialAnimationsDisplayed -> {
            _uiState.update {
                it.copy(
                    initialAnimationsDisplayed = true,
                    displayInitialAnimations = null,
                )
            }
        }*//*
    }*/

    /*private fun displayInitialAnimations() {
        viewModelScope.launch(Dispatchers.Default) {
            delay(300)
            _uiState.update { it.copy(displayInitialAnimations = Unit) }
        }
    }*/

    private suspend fun setupContentByArtworkId(id: Long) {
        /*useCases.observeArtworkWithManifest(id).catch {
        }.collect { artwork ->
            setupArtworkDetailScreen(artwork)
            if (recommendationsHaveNotBeenInitialized()) {
                fetchAndSetupRecommendedArtworks(artwork)
            }
        }*/
    }

    private fun fetchAndSetupRecommendedArtworks(artwork: Artwork) {
        viewModelScope.launch(Dispatchers.Default) {
            /*useCases.getRecommendedArtworksByArtwork(artwork).run {
                setupRecommendedArtworks(artworksRecommended, recommendationTypes)
            }*/
        }
    }

    /*private fun recommendationsHaveNotBeenInitialized(): Boolean {
        return _uiState.value.artworksRecommended == null
    }*/

    private fun setupRecommendedArtworks(
        artworksRecommended: List<Artwork>,
        recommendationTypes: List<RecommendationType>,
    ) {
        if (artworksRecommended.size != recommendationTypes.size) return
        val recommendationsUiState = artworksRecommended.mapIndexed { index, artworkRecommended ->
            ArtworkRecommendedUiState(
                id = artworkRecommended.id,
                imageUrl = artworkRecommended.imageUrl,
                title = artworkRecommended.title,
                reasonItWasRecommended = when (recommendationTypes[index]) {
                    is SameArtist -> {
                        Rui.string.reason_it_was_recommended_same_artist
                    }

                    is SameArtworkType -> {
                        Rui.string.reason_it_was_recommended_same_artwork_type
                    }

                    is SameCategory -> {
                        Rui.string.reason_it_was_recommended_same_category
                    }

                    is SameGallery -> {
                        Rui.string.reason_it_was_recommended_same_gallery
                    }
                },
            )
        }
        // _uiState.update { it.copy(artworksRecommended = recommendationsUiState) }
    }

    /*fun getHighQualityImageUrl(): String = useCases.getImageUrlUseCase(
        imageId = _uiState.value.artwork?.imageId.orEmpty(),
        qualityType = QualityImageType.ExtraHigh,
    )*/

    private fun setupArtworkDetailScreen(artwork: Artwork) {
        /*_uiState.update {
            it.copy(
                artwork = artwork.copy(
                    imageUrl = useCases.getImageUrlUseCase(
                        imageId = artwork.imageId,
                        qualityType = QualityImageType.ExtraHigh,
                    ),
                ),
            )
        }*/
    }
}
