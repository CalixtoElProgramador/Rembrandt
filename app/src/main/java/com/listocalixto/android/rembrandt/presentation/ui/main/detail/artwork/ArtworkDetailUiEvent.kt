package com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork

sealed interface ArtworkDetailUiEvent {
    data class Start(val artworkId: Long) : ArtworkDetailUiEvent
    data class OnArtworkRecommended(val position: Int) : ArtworkDetailUiEvent
    object OnBackPressed : ArtworkDetailUiEvent
}
