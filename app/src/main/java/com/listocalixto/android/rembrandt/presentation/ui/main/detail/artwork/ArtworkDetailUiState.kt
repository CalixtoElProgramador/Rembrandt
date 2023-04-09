package com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork

import com.listocalixto.android.rembrandt.core.Constants.EMPTY
import com.listocalixto.android.rembrandt.domain.entity.Artwork
import com.listocalixto.android.rembrandt.domain.utility.RecommendationType
import com.listocalixto.android.rembrandt.presentation.utility.UiText

data class ArtworkDetailUiState(
    val artwork: Artwork? = null,
    val descriptionUiText: UiText = UiText.StringValue(EMPTY),
    val artworksRecommended: List<ArtworkRecommendedUiState>? = null,
    val recommendationTypes: List<RecommendationType>? = null,
    val memoryCacheKey: String? = null
) {
    val imageUrl: String = artwork?.imageUrl ?: EMPTY
    val isFavorite: Boolean = artwork?.isFavorite ?: false
    val category: String = artwork?.translation?.category ?: artwork?.categoryTitles?.first() ?: EMPTY
    val title: String = artwork?.translation?.title ?: artwork?.title ?: EMPTY
    val artistName: String = artwork?.artistTitle ?: EMPTY
    val altText: String = artwork?.thumbnail?.altText ?: EMPTY
}
