package com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork

sealed interface ArtworkDetailUiEvent {
    object Start : ArtworkDetailUiEvent
    object SaveCurrentArtworkId : ArtworkDetailUiEvent
    object OnChipFavorite : ArtworkDetailUiEvent
}
