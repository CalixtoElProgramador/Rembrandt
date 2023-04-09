package com.listocalixto.android.rembrandt.presentation.ui.main.home

data class HomeUiState(
    val isLoading: Boolean = false,
    val artworks: List<ArtworkUiState> = emptyList()
)
