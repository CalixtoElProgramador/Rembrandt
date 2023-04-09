package com.listocalixto.android.rembrandt.domain.usecase.main

import com.listocalixto.android.rembrandt.domain.entity.Artwork
import com.listocalixto.android.rembrandt.domain.repo.ArtworkRepo
import com.listocalixto.android.rembrandt.domain.utility.QualityImageType.MEDIUM
import javax.inject.Inject

class GetAllArtworksUseCase @Inject constructor(
    private val repo: ArtworkRepo,
    private val getImageUrl: GetImageUrlUseCase
) {

    suspend operator fun invoke(): List<Artwork> {
        val artworks = repo.getAllArtworks()
        return artworks.map { it.copy(imageUrl = getImageUrl(it.imageId, MEDIUM)) }
    }
}
