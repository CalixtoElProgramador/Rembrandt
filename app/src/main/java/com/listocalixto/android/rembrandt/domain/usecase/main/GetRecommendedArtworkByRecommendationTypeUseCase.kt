package com.listocalixto.android.rembrandt.domain.usecase.main

import com.listocalixto.android.rembrandt.domain.entity.Artwork
import com.listocalixto.android.rembrandt.domain.utility.RecommendationType
import com.listocalixto.android.rembrandt.domain.utility.RecommendationType.SameArtist
import com.listocalixto.android.rembrandt.domain.utility.RecommendationType.SameArtworkType
import com.listocalixto.android.rembrandt.domain.utility.RecommendationType.SameCategory
import com.listocalixto.android.rembrandt.domain.utility.RecommendationType.SameGallery

class GetRecommendedArtworkByRecommendationTypeUseCase() {

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
