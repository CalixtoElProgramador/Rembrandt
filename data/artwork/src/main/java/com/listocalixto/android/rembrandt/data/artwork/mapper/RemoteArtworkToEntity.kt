package com.listocalixto.android.rembrandt.data.artwork.mapper

import com.listocalixto.android.rembrandt.common.dependencies.Mapper
import com.listocalixto.android.rembrandt.common.entities.Artwork
import com.listocalixto.android.rembrandt.core.domain.usecase.GetImageUrlUseCase
import com.listocalixto.android.rembrandt.core.domain.utility.QualityImageType.High
import com.listocalixto.android.rembrandt.core.network.artwork.dto.RemoteArtwork
import javax.inject.Inject

internal class RemoteArtworkToEntity @Inject constructor(
    private val remoteColorToModel: RemoteColorToModel,
    private val remoteThumbnailToModel: RemoteThumbnailToModel,
    private val getImageUrlUseCase: GetImageUrlUseCase,
) : Mapper<RemoteArtwork, Artwork>() {
    override fun map(input: RemoteArtwork) = Artwork(
        artistDisplay = input.artistDisplay,
        artistId = input.artistId,
        artistTitle = input.artistTitle,
        artworkTypeId = input.artworkTypeId,
        artworkTypeTitle = input.artworkTypeTitle,
        categoryIds = input.categoryIds,
        categoryTitles = input.categoryTitles,
        color = input.color?.let { remoteColorToModel.map(it) },
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
        imageUrl = getImageUrlUseCase(input.imageId, High),
        latitude = input.latitude,
        longitude = input.longitude,
        mediumDisplay = input.mediumDisplay,
        placeOfOrigin = input.placeOfOrigin,
        score = input.score,
        termTitles = input.termTitles,
        thumbnail = remoteThumbnailToModel.map(input.thumbnail),
        title = input.title,
    )

    override fun reverseMap(input: Artwork) = RemoteArtwork(
        artistDisplay = input.artistDisplay,
        artistId = input.artistId,
        artistTitle = input.artistTitle,
        artworkTypeId = input.artworkTypeId,
        artworkTypeTitle = input.artworkTypeTitle,
        categoryIds = input.categoryIds,
        categoryTitles = input.categoryTitles,
        color = input.color?.let { remoteColorToModel.reverseMap(it) },
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
        latitude = input.latitude,
        longitude = input.longitude,
        mediumDisplay = input.mediumDisplay,
        placeOfOrigin = input.placeOfOrigin,
        score = input.score,
        termTitles = input.termTitles,
        thumbnail = remoteThumbnailToModel.reverseMap(input.thumbnail),
        title = input.title,
    )
}
