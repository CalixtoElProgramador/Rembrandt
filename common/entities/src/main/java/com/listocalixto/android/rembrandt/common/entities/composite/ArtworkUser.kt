package com.listocalixto.android.rembrandt.common.entities.composite

import com.listocalixto.android.rembrandt.common.entities.Artwork
import com.listocalixto.android.rembrandt.common.entities.User
import com.listocalixto.android.rembrandt.common.entities.model.Color
import com.listocalixto.android.rembrandt.common.entities.model.Thumbnail

data class ArtworkUser internal constructor(
    val artistDisplay: String,
    val artistId: Int?,
    val artistTitle: String?,
    val artworkTypeId: Int,
    val artworkTypeTitle: String,
    val categoryIds: List<String>,
    val categoryTitles: List<String>,
    val color: Color?,
    val creditLine: String,
    val dateDisplay: String?,
    val dateEnd: Int?,
    val dateStart: Int?,
    val dimensions: String?,
    val galleryId: Int?,
    val galleryTitle: String?,
    val hasNotBeenViewedMuch: Boolean,
    val id: Long,
    val isFavorite: Boolean,
    val imageId: String,
    val imageUrl: String,
    val latitude: Double?,
    val longitude: Double?,
    val mediumDisplay: String?,
    val placeOfOrigin: String?,
    val score: Double,
    val termTitles: List<String>,
    val thumbnail: Thumbnail,
    val title: String,
) {

    constructor(artwork: Artwork, user: User) : this(
        artistDisplay = artwork.artistDisplay,
        artistId = artwork.artistId,
        artistTitle = artwork.artistTitle,
        artworkTypeId = artwork.artworkTypeId,
        artworkTypeTitle = artwork.artworkTypeTitle,
        categoryIds = artwork.categoryIds,
        categoryTitles = artwork.categoryTitles,
        color = artwork.color,
        creditLine = artwork.creditLine,
        dateDisplay = artwork.dateDisplay,
        dateEnd = artwork.dateEnd,
        dateStart = artwork.dateStart,
        dimensions = artwork.dimensions,
        galleryId = artwork.galleryId,
        galleryTitle = artwork.galleryTitle,
        hasNotBeenViewedMuch = artwork.hasNotBeenViewedMuch,
        id = artwork.id,
        isFavorite = user.favoriteArtworks.contains(artwork.id),
        imageId = artwork.imageId,
        imageUrl = artwork.imageUrl,
        latitude = artwork.latitude,
        longitude = artwork.longitude,
        mediumDisplay = artwork.mediumDisplay,
        placeOfOrigin = artwork.placeOfOrigin,
        score = artwork.score,
        termTitles = artwork.termTitles,
        thumbnail = artwork.thumbnail,
        title = artwork.title,
    )
}
