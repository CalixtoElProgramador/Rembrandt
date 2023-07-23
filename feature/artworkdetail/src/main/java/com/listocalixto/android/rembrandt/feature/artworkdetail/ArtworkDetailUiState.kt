package com.listocalixto.android.rembrandt.feature.artworkdetail

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import com.listocalixto.android.rembrandt.common.entities.composite.ArtworkUser
import com.listocalixto.android.rembrandt.core.ui.utility.UiText

data class ArtworkDetailUiState(
    /*val artwork: Artwork? = null,
    val artworksRecommended: List<ArtworkRecommendedUiState>? = null,
    val recommendationTypes: List<RecommendationType>? = null,
    val memoryCacheKey: String? = null,
    val translate: Boolean = true,
    val triggerRefreshAnimation: Unit? = null,
    val errorMessage: UiText? = null,
    val loadingTranslation: Boolean = false,
    val displayInitialAnimations: Unit? = null,
    val initialAnimationsDisplayed: Boolean = false,
    val gradientColor: Int = Color.TRANSPARENT,*/
    val imageMemoryCacheKey: String? = null,
    val shouldShowEnterAnimations: Boolean = false,
    val errorMessage: UiText? = null,
    val artworkId: Long = -1,
    val imageAmbientColor: Int = Color.TRANSPARENT,
    private val artworkUser: ArtworkUser? = null,
) {
    val imageUrl: String = artworkUser?.imageUrl.orEmpty()
    val isFavorite: Boolean = artworkUser?.isFavorite ?: false
    val category: String = artworkUser?.categoryTitleDisplay.orEmpty()
    val title: String = artworkUser?.title.orEmpty()
    val artistName: String = artworkUser?.artistDisplay.orEmpty()
    val alternativeText: String = artworkUser?.thumbnail?.altText.orEmpty()
    val imageAmbientGradient = GradientDrawable(
        GradientDrawable.Orientation.TOP_BOTTOM,
        intArrayOf(imageAmbientColor, 0x00FFFFF),
    ).also { it.cornerRadius = 0f }
    val shouldStartFragmentTransition: Boolean = artworkUser != null
    val description: UiText? = null

    /*val isTranslationDisplayed: Boolean = *//*translate && artwork?.translation != null*//* false
    val imageUrl: String = artwork?.imageUrl ?: EMPTY
    val isFavorite: Boolean = *//*artwork?.isFavorite ?:*//* false
    val category: String = *//*if (translate && artwork?.translation != null) {
        artwork.translation!!.category
    } else {
        try {
            artwork?.categoryTitles?.first() ?: EMPTY
        } catch (e: NoSuchElementException) {
            EMPTY
        }
    }*//* EMPTY
    val title: String = *//*if (translate && artwork?.translation != null) {
        artwork.translation!!.title
    } else {
        artwork?.title ?: EMPTY
    }*//* EMPTY
    val descriptionUiText: UiText = *//*if (translate && artwork?.translation != null) {
        UiText.StringValue(artwork.translation!!.content)
    } else {
        artwork?.manifest?.description?.let { UiText.StringValue(it) }
            ?: UiText.StringResource(R.string.frag_artwork_detail_no_description_available)
    }*//* UiText.StringValue(EMPTY)
    val artistName: String = artwork?.artistTitle ?: EMPTY
    val altText: String = artwork?.thumbnail?.altText ?: EMPTY
    val gradientDrawable = GradientDrawable(
        GradientDrawable.Orientation.TOP_BOTTOM,
        intArrayOf(gradientColor, 0x00FFFFF),
    ).also { it.cornerRadius = 0f }*/
}
