package com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork

sealed interface ArtworkDetailUiEvent {
    object SaveCurrentArtworkId : ArtworkDetailUiEvent
    object OnChipFavorite : ArtworkDetailUiEvent
    object TranslateContent : ArtworkDetailUiEvent
    object RefreshAnimationTriggered : ArtworkDetailUiEvent
    object ErrorMessageTriggered : ArtworkDetailUiEvent
}
