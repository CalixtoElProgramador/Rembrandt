package com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork

sealed interface ArtworkDetailUiEvent {
    object SaveCurrentArtworkId : ArtworkDetailUiEvent
    object OnChipFavorite : ArtworkDetailUiEvent
    data class TranslateContent(val targetLang: String) : ArtworkDetailUiEvent
    object RefreshAnimationTriggered : ArtworkDetailUiEvent
    object ErrorMessageTriggered : ArtworkDetailUiEvent
}
