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
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.ArtistTitle
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.ArtworkType
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.Category
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.CreditLine
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.DateDisplay
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.Description
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.Inscriptions
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.MediumDisplay
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.PhysicalDimensions
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.PlaceOfOrigin
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.StyleTitle
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.Title
import com.listocalixto.android.rembrandt.core.ui.R
import com.listocalixto.android.rembrandt.core.ui.R.string.label_characteristic_title
import com.listocalixto.android.rembrandt.core.ui.R.string.no_description_available
import com.listocalixto.android.rembrandt.core.ui.R.string.no_inscriptions
import com.listocalixto.android.rembrandt.core.ui.R.string.uncategorized
import com.listocalixto.android.rembrandt.core.ui.R.string.unknown_artist
import com.listocalixto.android.rembrandt.core.ui.R.string.unknown_artwork_type
import com.listocalixto.android.rembrandt.core.ui.R.string.unknown_credit_line
import com.listocalixto.android.rembrandt.core.ui.R.string.unknown_date
import com.listocalixto.android.rembrandt.core.ui.R.string.unknown_medium
import com.listocalixto.android.rembrandt.core.ui.R.string.unknown_physical_dimensions
import com.listocalixto.android.rembrandt.core.ui.R.string.unknown_place_of_origin
import com.listocalixto.android.rembrandt.core.ui.R.string.unknown_style
import com.listocalixto.android.rembrandt.core.ui.R.string.unknown_title
import com.listocalixto.android.rembrandt.core.ui.navigation.BottomNavTabType
import com.listocalixto.android.rembrandt.core.ui.navigation.BottomNavTabType.Home
import com.listocalixto.android.rembrandt.core.ui.states.ArtworkCharacteristicUiState
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
    val extraHighDefinitionImageUrl: String = "",
    val comesFrom: BottomNavTabType = Home,
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
    val artistTitle: String? = artworkUser?.artistTitle
    private val artistTitleTranslated: String? = getTranslation(ArtistTitle)
    private val artistTitleUiText = getUiText(artistTitleTranslated, artistTitle, unknown_artist)
    val dateDisplay: String? = artworkUser?.dateDisplay
    private val dateDisplayTranslated: String? = getTranslation(DateDisplay)
    private val dateDisplayUiText =
        getUiText(dateDisplayTranslated, dateDisplay, unknown_date)
    val dimensions: String? = artworkUser?.dimensions
    private val dimensionsTranslated = getTranslation(PhysicalDimensions)
    private val dimensionsUiText =
        getUiText(dimensionsTranslated, dimensions, unknown_physical_dimensions)
    val alternativeText: String = artworkUser?.thumbnail?.altText.orEmpty()
    val imageAmbientGradient: Drawable = getImageAmbientGradient()
    val shouldStartFragmentTransition: Boolean = artworkUser != null
    val description: String? = manifest?.description
    private val descriptionTranslated: String? = getTranslation(key = Description)
    val descriptionUiText: UiText =
        getUiText(descriptionTranslated, description, no_description_available)
    val mediumDisplay: String? = artworkUser?.mediumDisplay
    private val mediumDisplayTranslated: String? = getTranslation(MediumDisplay)
    private val mediumDisplayUiText =
        getUiText(mediumDisplayTranslated, mediumDisplay, unknown_medium)
    val artworkTypeTitle: String? = artworkUser?.artworkTypeTitle
    private val artworkTypeTitleTranslated: String? = getTranslation(ArtworkType)
    private val artworkTypeTitleUiText =
        getUiText(artworkTypeTitleTranslated, artworkTypeTitle, unknown_artwork_type)
    val placeOfOrigin: String? = artworkUser?.placeOfOrigin
    private val placeOfOriginTranslated: String? = getTranslation(PlaceOfOrigin)
    private val placeOfOriginUiText =
        getUiText(placeOfOriginTranslated, placeOfOrigin, unknown_place_of_origin)
    val creditLine: String? = artworkUser?.creditLine
    private val creditLineTranslated: String? = getTranslation(CreditLine)
    private val creditLineUiText = getUiText(creditLineTranslated, creditLine, unknown_credit_line)
    val inscriptions: String? = artworkUser?.inscriptions
    private val inscriptionsTranslated: String? = getTranslation(Inscriptions)
    private val inscriptionsUiText =
        getUiText(inscriptionsTranslated, inscriptions, no_inscriptions)
    val styleTitle: String? = artworkUser?.styleTitle
    private val styleTitleTranslated: String? = getTranslation(StyleTitle)
    private val styleTitleUiText = getUiText(styleTitleTranslated, styleTitle, unknown_style)
    val characteristics = listOf(
        ArtworkCharacteristicUiState(
            label = R.string.label_characteristic_author,
            value = artistTitleUiText,
        ),
        ArtworkCharacteristicUiState(
            label = label_characteristic_title,
            value = titleUiText,
        ),
        ArtworkCharacteristicUiState(
            label = R.string.label_characteristic_place_of_origin,
            value = placeOfOriginUiText,
        ),
        ArtworkCharacteristicUiState(
            label = R.string.label_characteristic_date,
            value = dateDisplayUiText,
        ),
        ArtworkCharacteristicUiState(
            label = R.string.label_characteristic_medium,
            value = mediumDisplayUiText,
        ),
        ArtworkCharacteristicUiState(
            label = R.string.label_characteristic_style,
            value = styleTitleUiText,
        ),
        ArtworkCharacteristicUiState(
            label = R.string.label_characteristic_inscriptions,
            value = inscriptionsUiText,
        ),
        ArtworkCharacteristicUiState(
            label = R.string.label_characteristic_physical_dimensions,
            value = dimensionsUiText,
        ),
        ArtworkCharacteristicUiState(
            label = R.string.label_characteristic_artwork_type,
            value = artworkTypeTitleUiText,
        ),
        ArtworkCharacteristicUiState(
            label = R.string.label_characteristic_credit_line,
            value = creditLineUiText,
        ),
    )

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
