package com.listocalixto.android.rembrandt.presentation.ui.main

sealed interface MainUiEvent {
    data class SetLoading(val isLoading: Boolean) : MainUiEvent
}
