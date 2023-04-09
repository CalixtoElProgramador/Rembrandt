package com.listocalixto.android.rembrandt.data.mapper.remote

import com.listocalixto.android.rembrandt.core.Constants.EMPTY
import com.listocalixto.android.rembrandt.core.Mapper
import com.listocalixto.android.rembrandt.data.source.remote.implementation.model.ArtworkRemote
import com.listocalixto.android.rembrandt.domain.entity.Artwork
import com.listocalixto.android.rembrandt.domain.model.Color
import com.listocalixto.android.rembrandt.domain.model.Thumbnail
import javax.inject.Inject

class ArtworkRemoteToEntity @Inject constructor(
    private val colorMapper: ColorRemoteToDomain,
    private val thumbnailMapper: ThumbnailRemoteToDomain
) : Mapper<ArtworkRemote, Artwork> {

    override fun map(value: ArtworkRemote) = Artwork(
        artistDisplay = value.artistDisplay ?: "",
        artistId = value.artistId ?: -1,
        artistTitle = value.artistTitle ?: "",
        artworkTypeId = value.artworkTypeId ?: -1,
        artworkTypeTitle = value.artworkTypeTitle ?: "",
        categoryIds = value.categoryIds,
        categoryTitles = value.categoryTitles,
        color = value.color?.let { colorMapper.map(it) } ?: Color.defaultInstance,
        creditLine = value.creditLine ?: "",
        dateDisplay = value.dateDisplay ?: "",
        dateEnd = value.dateEnd ?: -1,
        dateStart = value.dateStart ?: -1,
        dimensions = value.dimensions ?: "",
        galleryId = value.galleryId ?: -1,
        galleryTitle = value.galleryTitle ?: "",
        hasNotBeenViewedMuch = value.hasNotBeenViewedMuch ?: false,
        id = value.id,
        isFavorite = value.isFavorite,
        imageId = value.imageId ?: "",
        imageUrl = EMPTY,
        latitude = value.latitude ?: -1.0,
        longitude = value.longitude ?: -1.0,
        mediumDisplay = value.mediumDisplay ?: "",
        placeOfOrigin = value.placeOfOrigin ?: "",
        score = value.score ?: -1.0,
        termTitles = value.termTitles,
        thumbnail = value.thumbnail?.let { thumbnailMapper.map(it) } ?: Thumbnail.defaultInstance,
        title = value.title ?: ""
    )

    override fun map(values: List<ArtworkRemote>) = values.map { map(it) }

    override fun reverseMap(value: Artwork) = ArtworkRemote(
        artistDisplay = value.artistDisplay,
        artistId = value.artistId,
        artistTitle = value.artistTitle,
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

    override fun reverseMap(values: List<Artwork>): List<ArtworkRemote> {
        return values.map { reverseMap(it) }
    }
}
