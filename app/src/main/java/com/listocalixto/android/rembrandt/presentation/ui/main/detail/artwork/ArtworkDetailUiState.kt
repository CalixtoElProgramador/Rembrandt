package com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork

import com.listocalixto.android.rembrandt.core.Constants.EMPTY
import com.listocalixto.android.rembrandt.presentation.utility.UiText

data class ArtworkDetailUiState(
    val imageUrl: String = "",
    val isFavorite: Boolean = false,
    val category: String = "",
    val title: String = "",
    val artistName: String = "",
    val descriptionUiText: UiText = UiText.StringValue(EMPTY),
    val artworksRecommended: List<ArtworkRecommendedUiState> = emptyList(),
    val memoryCacheKey: String? = null,
    val altText: String = ""
)
