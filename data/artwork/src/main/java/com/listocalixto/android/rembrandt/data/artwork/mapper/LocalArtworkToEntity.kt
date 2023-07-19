package com.listocalixto.android.rembrandt.data.artwork.mapper

import com.listocalixto.android.rembrandt.common.dependencies.Mapper
import com.listocalixto.android.rembrandt.common.entities.Artwork
import com.listocalixto.android.rembrandt.core.local.artwork.table.LocalArtwork
import javax.inject.Inject

internal class LocalArtworkToEntity @Inject constructor(
    private val localColorToModel: LocalColorToModel,
    private val localThumbnailToModel: LocalThumbnailToModel,
) : Mapper<LocalArtwork, Artwork>() {
    override fun map(input: LocalArtwork) = Artwork(
        artistDisplay = input.artistDisplay,
        artistId = input.artistId,
        artistTitle = input.artistTitle,
        artworkTypeId = input.artworkTypeId,
        artworkTypeTitle = input.artworkTypeTitle,
        categoryIds = input.categoryIds,
        categoryTitles = input.categoryTitles,
        color = input.color?.let { localColorToModel.map(it) },
        creditLine = input.creditLine,
        dateDisplay = input.dateDisplay,
        dateEnd = input.dateEnd,
        dateStart = input.dateStart,
        dimensions = input.dimensions,
        galleryId = input.galleryId,
        galleryTitle = input.galleryTitle,
        hasNotBeenViewedMuch = input.hasNotBeenViewedMuch,
        id = input.id,
        imageId = input.imageId,
        imageUrl = input.imageUrl,
        latitude = input.latitude,
        longitude = input.longitude,
        mediumDisplay = input.mediumDisplay,
        placeOfOrigin = input.placeOfOrigin,
        score = input.score,
        termTitles = input.termTitles,
        thumbnail = localThumbnailToModel.map(input.thumbnail),
        title = input.title,
    )

    override fun reverseMap(input: Artwork) = LocalArtwork(
        artistDisplay = input.artistDisplay,
        artistId = input.artistId,
        artistTitle = input.artistTitle,
        artworkTypeId = input.artworkTypeId,
        artworkTypeTitle = input.artworkTypeTitle,
        categoryIds = input.categoryIds,
        categoryTitles = input.categoryTitles,
        color = input.color?.let { localColorToModel.reverseMap(it) },
        creditLine = input.creditLine,
        dateDisplay = input.dateDisplay,
        dateEnd = input.dateEnd,
        dateStart = input.dateStart,
        dimensions = input.dimensions,
        galleryId = input.galleryId,
        galleryTitle = input.galleryTitle,
        hasNotBeenViewedMuch = input.hasNotBeenViewedMuch,
        id = input.id,
        imageId = input.imageId,
        imageUrl = input.imageUrl,
        latitude = input.latitude,
        longitude = input.longitude,
        mediumDisplay = input.mediumDisplay,
        placeOfOrigin = input.placeOfOrigin,
        score = input.score,
        termTitles = input.termTitles,
        thumbnail = localThumbnailToModel.reverseMap(input.thumbnail),
        title = input.title,
    )
}
