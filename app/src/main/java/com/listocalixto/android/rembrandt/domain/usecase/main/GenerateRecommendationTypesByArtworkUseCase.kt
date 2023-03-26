package com.listocalixto.android.rembrandt.domain.usecase.main

import com.listocalixto.android.rembrandt.domain.entity.Artwork
import com.listocalixto.android.rembrandt.domain.utility.RecommendationType

class GenerateRecommendationTypesByArtworkUseCase {

    operator fun invoke(artwork: Artwork) = listOf(
        RecommendationType.SameArtist(artwork.artistId.toString()),
        RecommendationType.SameCategory(artwork.categoryIds.first()),
        RecommendationType.SameArtworkType(artwork.artworkTypeId.toString()),
        RecommendationType.SameGallery(artwork.galleryId.toString()),
    )
}
