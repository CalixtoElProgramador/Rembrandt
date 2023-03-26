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
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.ArtworkDetailUiEvent.OnArtworkRecommended
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.ArtworkDetailUiEvent.OnBackPressed
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.ArtworkDetailUiEvent.Start
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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

    fun onEvent(event: ArtworkDetailUiEvent): Unit = when (event) {
        is Start -> {
            viewModelScope.launch(viewModelDispatcher) {
                setupContentByArtworkId(event.artworkId)
            }
            Unit
        }
        is OnArtworkRecommended -> {
            viewModelScope.launch(viewModelDispatcher) {
                addArtworkInTheBackStack()
                val artworksRecommended = data.value.artworksRecommended
                val artworkClicked = artworksRecommended[event.position]
                setupContentByArtworkId(artworkClicked.id)
            }
            Unit
        }
        OnBackPressed -> {
            val dataInTheBackStack = _uiState.value.dataInTheBackStack
            if (dataInTheBackStack.isNotEmpty()) {
                val previousData = dataInTheBackStack.last()
                previousData.artwork?.let { previousArtwork ->
                    dataInTheBackStack.removeLast()
                    setupContent(
                        artwork = previousArtwork,
                        artworksRecommended = previousData.artworksRecommended,
                        recommendationTypes = previousData.recommendationTypes,
                    )
                }
            }

            Unit
        }
    }

    private fun addArtworkInTheBackStack() {
        val currentData = data.value
        val dataInTheBackStack = _uiState.value.dataInTheBackStack
        if (dataInTheBackStack.add(currentData)) {
            _uiState.update { it.copy(dataInTheBackStack = dataInTheBackStack) }
        }
    }

    private suspend fun setupContentByArtworkId(id: Long) {
        useCases.observeArtworkDetail(id).collect { result ->
            result.onSuccess { useCaseResult ->
                setupContent(
                    artwork = useCaseResult.artwork,
                    artworksRecommended = useCaseResult.artworksRecommended,
                    recommendationTypes = useCaseResult.recommendationTypes,
                )
            }.onFailure {
            }
        }
    }

    private fun setupContent(
        artwork: Artwork,
        artworksRecommended: List<Artwork>,
        recommendationTypes: List<RecommendationType>,
    ) = Unit.run {
        data.update {
            it.copy(
                artwork = artwork,
                artworksRecommended = artworksRecommended,
                recommendationTypes = recommendationTypes,
            )
        }
        val mArtwork = data.value.artwork ?: return@run
        val mArtworksRecommended = data.value.artworksRecommended
        val mRecommendationTypes = data.value.recommendationTypes
        if (mArtworksRecommended.size != mRecommendationTypes.size) return@run
        val recommendationsUiState =
            mArtworksRecommended.mapIndexed { index, artworkRecommended ->
                ArtworkRecommendedUiState(
                    imageUrl = artworkRecommended.imageUrl,
                    title = artworkRecommended.title,
                    reasonItWasRecommended = when (mRecommendationTypes[index]) {
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
        _uiState.update {
            it.copy(
                imageUrl = mArtwork.imageUrl,
                isFavorite = mArtwork.isFavorite,
                category = mArtwork.categoryTitles.first(),
                title = mArtwork.title,
                artistName = mArtwork.artistTitle,
                description = mArtwork.thumbnail.altText,
                artworksRecommended = recommendationsUiState,
            )
        }
    }
}
