package com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.listocalixto.android.rembrandt.R
import com.listocalixto.android.rembrandt.domain.entity.Artwork
import com.listocalixto.android.rembrandt.domain.usecase.main.ArtworkDetailUseCases
import com.listocalixto.android.rembrandt.domain.utility.RecommendationType
import com.listocalixto.android.rembrandt.domain.utility.RecommendationType.SameArtist
import com.listocalixto.android.rembrandt.domain.utility.RecommendationType.SameArtworkType
import com.listocalixto.android.rembrandt.domain.utility.RecommendationType.SameCategory
import com.listocalixto.android.rembrandt.domain.utility.RecommendationType.SameGallery
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.ArtworkDetailFragment.Companion.ARTWORK_ID_DEFAULT_VALUE
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.ArtworkDetailFragment.Companion.ARTWORK_ID_KEY
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.ArtworkDetailUiEvent.OnChipFavorite
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.ArtworkDetailUiEvent.SaveCurrentArtworkId
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.ArtworkDetailUiEvent.Start
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtworkDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useCases: ArtworkDetailUseCases,
) : ViewModel() {

    private val data = MutableStateFlow(ArtworkDetailData())
    private val _uiState = MutableStateFlow(ArtworkDetailUiState())
    val uiState = _uiState.asStateFlow()
    private val viewModelDispatcher = viewModelScope.coroutineContext + Dispatchers.Main

    private var updateArtworkJob: Job? = null

    fun onEvent(event: ArtworkDetailUiEvent): Unit = when (event) {
        is Start -> {
            viewModelScope.launch(viewModelDispatcher) {
                savedStateHandle.getStateFlow(ARTWORK_ID_KEY, ARTWORK_ID_DEFAULT_VALUE)
                    .collect { artworkId ->
                        val artwork = data.value.artwork
                        val artworksRecommended = data.value.artworksRecommended
                        val recommendedTypes = data.value.recommendationTypes
                        if (artwork != null && artworksRecommended != null && recommendedTypes != null) {
                            // Data is kept in memory
                            setupArtwork(artwork)
                            setupRecommendedArtworks(artworksRecommended, recommendedTypes)
                        } else {
                            setupContentByArtworkId(artworkId)
                        }
                    }
            }
            Unit
        }
        SaveCurrentArtworkId -> {
            savedStateHandle[ARTWORK_ID_KEY] = data.value.artwork?.id ?: ARTWORK_ID_DEFAULT_VALUE
        }
        OnChipFavorite -> Unit.apply {
            if (updateArtworkJob != null) return@apply
            updateArtworkJob = viewModelScope.launch(viewModelDispatcher) {
                val currentArtwork = data.value.artwork ?: run {
                    updateArtworkJob = null
                    return@launch
                }
                useCases.updateArtwork(currentArtwork.copy(isFavorite = !currentArtwork.isFavorite))
                updateArtworkJob = null
            }
        }
    }

    private suspend fun setupContentByArtworkId(id: Long) {
        useCases.observeArtworkById(id).collect { result ->
            result.onSuccess { artwork ->
                setupArtwork(artwork)
                if (recommendationsHaveNotBeenInitialized()) {
                    fetchAndSetupRecommendedArtworks(artwork)
                }
            }.onFailure {
            }
        }
    }

    private suspend fun fetchAndSetupRecommendedArtworks(artwork: Artwork) {
        useCases.getRecommendedArtworksByArtwork(artwork).apply {
            setupRecommendedArtworks(artworksRecommended, recommendationTypes)
        }
    }

    private fun recommendationsHaveNotBeenInitialized(): Boolean {
        return data.value.artworksRecommended == null || data.value.recommendationTypes == null
    }

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
                },
            )
        }
        data.update {
            it.copy(
                artworksRecommended = artworksRecommended,
                recommendationTypes = recommendationTypes,
            )
        }
        _uiState.update { it.copy(artworksRecommended = recommendationsUiState) }
    }

    private fun setupArtwork(artwork: Artwork) {
        data.update { it.copy(artwork = artwork) }
        _uiState.update {
            it.copy(
                imageUrl = artwork.imageUrl,
                isFavorite = artwork.isFavorite,
                category = artwork.categoryTitles.first(),
                title = artwork.title,
                artistName = artwork.artistTitle,
                description = artwork.thumbnail.altText,
            )
        }
    }
}
