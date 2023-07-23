package com.listocalixto.android.rembrandt.common.entities.composite

import com.listocalixto.android.rembrandt.common.entities.Manifest
import com.listocalixto.android.rembrandt.common.entities.Translation
import com.listocalixto.android.rembrandt.common.entities.model.Color
import com.listocalixto.android.rembrandt.common.entities.model.Thumbnail
import com.listocalixto.android.rembrandt.common.entities.utility.Constants.EMPTY

data class ArtworkManifestTranslationUser internal constructor(
    val artistId: Int?,
    val artistDisplay: String,
    val artistTitle: String?,
    val artworkTypeId: Int,
    val artworkTypeTitle: String,
    val attribution: String,
    val categoryIds: List<String>,
    val categoryTitleDisplay: String?,
    val categoryTitleDisplayTranslated: String?,
    val categoryTitles: List<String>,
    val color: Color?,
    val creditLine: String,
    val dateDisplay: String?,
    val dateEnd: Int?,
    val dateStart: Int?,
    val description: String,
    val descriptionTranslated: String?,
    val dimensions: String?,
    val galleryId: Int?,
    val galleryTitle: String?,
    val hasNotBeenViewedMuch: Boolean,
    val id: Long,
    val imageId: String,
    val imageUrl: String,
    val isFavorite: Boolean,
    val latitude: Double?,
    val logoAttribution: String,
    val longitude: Double?,
    val mediumDisplay: String?,
    val placeOfOrigin: String?,
    val score: Double,
    val termTitles: List<String>,
    val thumbnail: Thumbnail,
    val title: String,
    val titleTranslated: String?,
) {
    constructor(
        artworkUser: ArtworkUser,
        manifest: Manifest,
        translation: Translation?,
    ) : this(
        artistId = artworkUser.artistId,
        artistDisplay = artworkUser.artistDisplay,
        artistTitle = artworkUser.artistTitle,
        artworkTypeId = artworkUser.artworkTypeId,
        artworkTypeTitle = artworkUser.artworkTypeTitle,
        attribution = manifest.attribution,
        categoryIds = artworkUser.categoryIds,
        categoryTitleDisplay = artworkUser.categoryTitleDisplay,
        categoryTitleDisplayTranslated = translation?.keysAndTranslations?.getOrDefault(
            CATEGORY_TITLE_DISPLAY_TRANSLATION_KEY,
            EMPTY,
        ),
        categoryTitles = artworkUser.categoryTitles,
        color = artworkUser.color,
        creditLine = artworkUser.creditLine,
        dateDisplay = artworkUser.dateDisplay,
        dateEnd = artworkUser.dateEnd,
        dateStart = artworkUser.dateStart,
        description = manifest.description,
        descriptionTranslated = translation?.keysAndTranslations?.getOrDefault(
            DESCRIPTION_TRANSLATION_KEY,
            EMPTY,
        ),
        dimensions = artworkUser.dimensions,
        galleryId = artworkUser.galleryId,
        galleryTitle = artworkUser.galleryTitle,
        hasNotBeenViewedMuch = artworkUser.hasNotBeenViewedMuch,
        id = artworkUser.id,
        imageId = artworkUser.imageId,
        imageUrl = artworkUser.imageUrl,
        isFavorite = artworkUser.isFavorite,
        latitude = artworkUser.latitude,
        logoAttribution = manifest.logoAttribution,
        longitude = artworkUser.longitude,
        mediumDisplay = artworkUser.mediumDisplay,
        placeOfOrigin = artworkUser.placeOfOrigin,
        score = artworkUser.score,
        termTitles = artworkUser.termTitles,
        thumbnail = artworkUser.thumbnail,
        title = artworkUser.title,
        titleTranslated = translation?.keysAndTranslations?.getOrDefault(
            TITLE_TRANSLATION_KEY,
            EMPTY,
        ),
    )

    companion object {
        const val CATEGORY_TITLE_DISPLAY_TRANSLATION_KEY = "CATEGORY_TITLE_DISPLAY_TRANSLATION_KEY"
        const val DESCRIPTION_TRANSLATION_KEY = "DESCRIPTION_TRANSLATION_KEY"
        const val TITLE_TRANSLATION_KEY = "TITLE_TRANSLATION_KEY"
    }
}
