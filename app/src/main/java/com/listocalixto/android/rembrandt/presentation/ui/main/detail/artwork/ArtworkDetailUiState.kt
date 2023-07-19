package com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import com.listocalixto.android.rembrandt.common.entities.Artwork
import com.listocalixto.android.rembrandt.common.entities.utility.Constants.EMPTY
import com.listocalixto.android.rembrandt.common.entities.utility.RecommendationType
import com.listocalixto.android.rembrandt.core.ui.utility.UiText
import com.listocalixto.android.rembrandt.core.ui.states.ArtworkRecommendedUiState

data class ArtworkDetailUiState(
    val artwork: Artwork? = null,
    val artworksRecommended: List<ArtworkRecommendedUiState>? = null,
    val recommendationTypes: List<RecommendationType>? = null,
    val memoryCacheKey: String? = null,
    val translate: Boolean = true,
    val triggerRefreshAnimation: Unit? = null,
    val errorMessage: UiText? = null,
    val loadingTranslation: Boolean = false,
    val displayInitialAnimations: Unit? = null,
    val initialAnimationsDisplayed: Boolean = false,
    val gradientColor: Int = Color.TRANSPARENT,
) {
    val isTranslationDisplayed: Boolean = /*translate && artwork?.translation != null*/ false
    val imageUrl: String = artwork?.imageUrl ?: EMPTY
    val isFavorite: Boolean = /*artwork?.isFavorite ?:*/ false
    val category: String = /*if (translate && artwork?.translation != null) {
        artwork.translation!!.category
    } else {
        try {
            artwork?.categoryTitles?.first() ?: EMPTY
        } catch (e: NoSuchElementException) {
            EMPTY
        }
    }*/ EMPTY
    val title: String = /*if (translate && artwork?.translation != null) {
        artwork.translation!!.title
    } else {
        artwork?.title ?: EMPTY
    }*/ EMPTY
    val descriptionUiText: UiText = /*if (translate && artwork?.translation != null) {
        UiText.StringValue(artwork.translation!!.content)
    } else {
        artwork?.manifest?.description?.let { UiText.StringValue(it) }
            ?: UiText.StringResource(R.string.frag_artwork_detail_no_description_available)
    }*/ UiText.StringValue(EMPTY)
    val artistName: String = artwork?.artistTitle ?: EMPTY
    val altText: String = artwork?.thumbnail?.altText ?: EMPTY
    val gradientDrawable = GradientDrawable(
        GradientDrawable.Orientation.TOP_BOTTOM,
        intArrayOf(gradientColor, 0x00FFFFF),
    ).also { it.cornerRadius = 0f }
}
