package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.core.domain.repository.ArtworkRepo
import javax.inject.Inject

class GetAllArtworksUseCase @Inject constructor(
    private val repo: ArtworkRepo,
    private val getImageUrl: GetImageUrlUseCase,
) {
    /*suspend operator fun invoke(): List<Artwork> {
        val artworks = repo.getAllArtworks()
        return artworks.map { it.copy(imageUrl = getImageUrl(it.imageId, High)) }
    }*/
}
