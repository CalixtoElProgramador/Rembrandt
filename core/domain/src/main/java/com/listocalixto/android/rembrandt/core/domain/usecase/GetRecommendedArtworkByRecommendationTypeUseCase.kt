package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.common.entities.Artwork
import com.listocalixto.android.rembrandt.common.entities.utility.RecommendationType
import com.listocalixto.android.rembrandt.common.entities.utility.RecommendationType.SameArtist
import com.listocalixto.android.rembrandt.common.entities.utility.RecommendationType.SameArtworkType
import com.listocalixto.android.rembrandt.common.entities.utility.RecommendationType.SameCategory
import com.listocalixto.android.rembrandt.common.entities.utility.RecommendationType.SameGallery
import javax.inject.Inject

class GetRecommendedArtworkByRecommendationTypeUseCase @Inject constructor() {
    operator fun invoke(type: RecommendationType, artworks: List<Artwork>): Artwork? {
        val recommendations = when (type) {
            is SameArtist -> {
                artworks.filter { type.id == it.artistId.toString() }
            }
            is SameArtworkType -> {
                artworks.filter { type.id == it.artworkTypeId.toString() }
            }
            is SameCategory -> {
                artworks.filter { type.id == it.categoryIds.first().toString() }
            }
            is SameGallery -> {
                artworks.filter { type.id == it.galleryId.toString() }
            }
        }
        return if (recommendations.isEmpty()) {
            null
        } else {
            recommendations.random()
        }
    }
}
