package com.listocalixto.android.rembrandt.data.mapper.local

import com.listocalixto.android.rembrandt.core.Constants.EMPTY
import com.listocalixto.android.rembrandt.core.Mapper
import com.listocalixto.android.rembrandt.data.source.local.implementation.table.main.ArtworkTable
import com.listocalixto.android.rembrandt.domain.entity.Artwork
import javax.inject.Inject

class ArtworkLocalToEntity @Inject constructor(
    private val colorMapper: ColorLocalToDomain,
    private val thumbnailMapper: ThumbnailLocalToDomain
) : Mapper<ArtworkTable, Artwork> {
    override fun map(value: ArtworkTable) = Artwork(
        artistDisplay = value.artistDisplay,
        artistId = value.artistId,
        artistTitle = value.artistTitle,
        artworkTypeId = value.artworkTypeId,
        artworkTypeTitle = value.artworkTypeTitle,
        categoryIds = value.categoryIds,
        categoryTitles = value.categoryTitles,
        color = colorMapper.map(value.color),
        creditLine = value.creditLine,
        dateDisplay = value.dateDisplay,
        dateEnd = value.dateEnd,
        dateStart = value.dateStart,
        dimensions = value.dimensions,
        galleryId = value.galleryId,
        galleryTitle = value.galleryTitle,
        hasNotBeenViewedMuch = value.hasNotBeenViewedMuch,
        id = value.id,
        isFavorite = value.isFavorite,
        imageId = value.imageId,
        imageUrl = EMPTY,
        latitude = value.latitude,
        longitude = value.longitude,
        mediumDisplay = value.mediumDisplay,
        placeOfOrigin = value.placeOfOrigin,
        score = value.score,
        termTitles = value.termTitles,
        thumbnail = thumbnailMapper.map(value.thumbnail),
        title = value.title
    )

    override fun map(values: List<ArtworkTable>): List<Artwork> {
        return values.map { map(it) }
    }

    override fun reverseMap(value: Artwork) = ArtworkTable(
        artistDisplay = value.artistDisplay,
        artistId = value.artistId,
        artistTitle = value.artistTitle.ifEmpty { "Unknown" },
        artworkTypeId = value.artworkTypeId,
        artworkTypeTitle = value.artworkTypeTitle,
        categoryIds = value.categoryIds,
        categoryTitles = value.categoryTitles,
        color = colorMapper.reverseMap(value.color),
        creditLine = value.creditLine,
        dateDisplay = value.dateDisplay,
        dateEnd = value.dateEnd,
        dateStart = value.dateStart,
        dimensions = value.dimensions,
        galleryId = value.galleryId,
        galleryTitle = value.galleryTitle,
        hasNotBeenViewedMuch = value.hasNotBeenViewedMuch,
        id = value.id,
        isFavorite = value.isFavorite,
        imageId = value.imageId,
        latitude = value.latitude,
        longitude = value.longitude,
        mediumDisplay = value.mediumDisplay,
        placeOfOrigin = value.placeOfOrigin,
        score = value.score,
        termTitles = value.termTitles,
        thumbnail = thumbnailMapper.reverseMap(value.thumbnail),
        title = value.title
    )

    override fun reverseMap(values: List<Artwork>): List<ArtworkTable> {
        return values.map { reverseMap(it) }
    }
}
