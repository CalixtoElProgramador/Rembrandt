package com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork

sealed interface ArtworkDetailUiEvent {
    object Start : ArtworkDetailUiEvent
    data class OnArtworkRecommended(val artworkId: Long) : ArtworkDetailUiEvent
    object OnBackPressed : ArtworkDetailUiEvent
    object SaveCurrentArtworkId : ArtworkDetailUiEvent
}
