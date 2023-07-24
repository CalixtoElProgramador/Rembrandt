package com.listocalixto.android.rembrandt.feature.artworkdetail

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import androidx.annotation.StringRes
import com.listocalixto.android.rembrandt.common.entities.Manifest
import com.listocalixto.android.rembrandt.common.entities.Translation
import com.listocalixto.android.rembrandt.common.entities.composite.ArtworkUser
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.ArtistDisplay
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.Category
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.Description
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.Title
import com.listocalixto.android.rembrandt.core.ui.R.string.no_description_available
import com.listocalixto.android.rembrandt.core.ui.R.string.uncategorized
import com.listocalixto.android.rembrandt.core.ui.R.string.unknown_artist
import com.listocalixto.android.rembrandt.core.ui.R.string.unknown_title
import com.listocalixto.android.rembrandt.core.ui.states.RecommendedArtworksUiState
import com.listocalixto.android.rembrandt.core.ui.utility.UiText

data class ArtworkDetailUiState(
    val imageMemoryCacheKey: String? = null,
    val triggerEnterAnimations: Unit? = null,
    val wereEnterAnimationsShown: Boolean = false,
    val errorMessage: UiText? = null,
    val artworkId: Long = -1,
    val imageAmbientColor: Int = Color.TRANSPARENT,
    val recommendedArtworks: List<RecommendedArtworksUiState> = listOf(),
    val shouldShowOriginalLanguage: Boolean = true,
    val isLoadingTranslation: Boolean = false,
    val triggerTranslationAnimation: Unit? = null,
    private val artworkUser: ArtworkUser? = null,
    private val manifest: Manifest? = null,
    private val translation: Translation? = null,
) {
    val imageUrl: String = artworkUser?.imageUrl.orEmpty()
    val isFavorite: Boolean = artworkUser?.isFavorite.orFalse()
    val isTranslationAvailable: Boolean = translation != null
    private val shouldShowTranslation: Boolean =
        isTranslationAvailable && !shouldShowOriginalLanguage
    val category: String? = artworkUser?.categoryTitleDisplay
    private val categoryTranslated: String? = getTranslation(key = Category)
    val categoryUiText: UiText = getUiText(categoryTranslated, category, uncategorized)
    val title: String? = artworkUser?.title
    private val titleTranslated: String? = getTranslation(key = Title)
    val titleUiText: UiText = getUiText(titleTranslated, title, unknown_title)
    val artistDisplay: String? = artworkUser?.artistDisplay
    private val artistDisplayTranslated: String? = getTranslation(ArtistDisplay)
    val artistDisplayUiText = getUiText(artistDisplayTranslated, artistDisplay, unknown_artist)
    val alternativeText: String = artworkUser?.thumbnail?.altText.orEmpty()
    val imageAmbientGradient: Drawable = getImageAmbientGradient()
    val shouldStartFragmentTransition: Boolean = artworkUser != null
    val description: String? = manifest?.description
    private val descriptionTranslated: String? = getTranslation(key = Description)
    val descriptionUiText: UiText =
        getUiText(descriptionTranslated, description, no_description_available)

    private fun getUiText(
        textTranslated: String?,
        originalText: String?,
        @StringRes defaultStringRes: Int,
    ) = when {
        textTranslated != null && shouldShowTranslation -> UiText.StringValue(textTranslated)
        originalText != null -> UiText.StringValue(originalText)
        else -> UiText.StringResource(defaultStringRes)
    }

    private fun Boolean?.orFalse(): Boolean = this ?: false

    private fun getImageAmbientGradient() = GradientDrawable(
        GradientDrawable.Orientation.TOP_BOTTOM,
        intArrayOf(imageAmbientColor, 0x00FFFFF),
    ).also { it.cornerRadius = 0f }

    private fun getTranslation(key: ArtworkTranslationKey) =
        translation?.keysAndTranslations?.get(key.name)
}
