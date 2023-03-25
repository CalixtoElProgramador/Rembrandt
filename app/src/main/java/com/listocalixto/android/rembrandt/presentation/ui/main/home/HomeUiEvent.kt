package com.listocalixto.android.rembrandt.presentation.ui.main.home

sealed interface HomeUiEvent {
    data class ObFavorite(val artworkId: Long) : HomeUiEvent
    data class OnShare(val artworkId: Long) : HomeUiEvent
}
