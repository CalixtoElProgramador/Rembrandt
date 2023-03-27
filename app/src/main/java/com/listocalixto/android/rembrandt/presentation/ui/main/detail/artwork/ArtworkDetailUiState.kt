package com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork

data class ArtworkDetailUiState(
    val imageUrl: String = "",
    val isFavorite: Boolean = false,
    val category: String = "",
    val title: String = "",
    val artistName: String = "",
    val description: String = "",
    val artworksRecommended: List<ArtworkRecommendedUiState> = emptyList(),
)
