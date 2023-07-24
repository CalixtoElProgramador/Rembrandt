package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.common.entities.Artwork
import com.listocalixto.android.rembrandt.common.entities.utility.RecommendationType.SameArtist
import com.listocalixto.android.rembrandt.common.entities.utility.RecommendationType.SameArtworkType
import com.listocalixto.android.rembrandt.common.entities.utility.RecommendationType.SameCategory
import com.listocalixto.android.rembrandt.common.entities.utility.RecommendationType.SameGallery
import javax.inject.Inject

class GetRecommendationTypesByArtworkUseCase @Inject constructor() {
    operator fun invoke(artwork: Artwork) = listOf(
        SameArtist(artwork.artistId.toString()),
        SameCategory(artwork.categoryIds.first()),
        SameArtworkType(artwork.artworkTypeId.toString()),
        SameGallery(artwork.galleryId.toString()),
    )
}
