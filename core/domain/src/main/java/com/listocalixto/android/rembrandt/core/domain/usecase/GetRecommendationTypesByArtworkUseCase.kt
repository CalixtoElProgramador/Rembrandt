package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.common.entities.Artwork
import com.listocalixto.android.rembrandt.common.entities.utility.RecommendationType
import javax.inject.Inject

class GetRecommendationTypesByArtworkUseCase @Inject constructor() {
    operator fun invoke(artwork: Artwork) = listOf(
        RecommendationType.SameArtist(artwork.artistId.toString()),
        RecommendationType.SameCategory(artwork.categoryIds.first()),
        RecommendationType.SameArtworkType(artwork.artworkTypeId.toString()),
        RecommendationType.SameGallery(artwork.galleryId.toString()),
    )
}
