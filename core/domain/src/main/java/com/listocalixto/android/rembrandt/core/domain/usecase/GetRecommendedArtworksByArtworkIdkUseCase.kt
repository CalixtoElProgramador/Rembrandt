package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.common.entities.Artwork
import com.listocalixto.android.rembrandt.common.entities.utility.RecommendationType
import com.listocalixto.android.rembrandt.core.domain.repository.ArtworkRepo
import javax.inject.Inject

class GetRecommendedArtworksByArtworkIdkUseCase @Inject constructor(
    private val artworkRepo: ArtworkRepo,
    private val getArtworkByRecommendationType: GetArtworkByRecommendationTypeUseCase,
    private val getRecommendationTypesByArtwork: GetRecommendationTypesByArtworkUseCase,
) {

    suspend operator fun invoke(id: Long): Map<Artwork, RecommendationType> {
        val allArtworks = artworkRepo.getAllArtworks()
        val currentArtwork = allArtworks.firstOrNull { it.id == id } ?: return mapOf()
        val recommendedArtworks = mutableListOf<Artwork>()
        val recommendationTypes = getRecommendationTypesByArtwork(currentArtwork)
        val recommendationTypesApplied = mutableListOf<RecommendationType>()
        var attemptsUsed = 0
        while (recommendedArtworks.size < MAXIMUM_RECOMMENDATIONS && attemptsUsed <= MAXIMUM_ATTEMPTS) {
            val randomType = recommendationTypes.random()
            val artworkRecommended = getArtworkByRecommendationType(randomType, allArtworks)
            if (artworkRecommended == null) {
                attemptsUsed++
                continue
            }
            val alreadyAdded = recommendedArtworks.any { artworkRecommended.id == it.id }
            val isTheSameAsTheCurrentArtwork = currentArtwork.id == artworkRecommended.id
            if (alreadyAdded || isTheSameAsTheCurrentArtwork) {
                attemptsUsed++
                continue
            }
            recommendedArtworks.add(artworkRecommended); recommendationTypesApplied.add(randomType)
            attemptsUsed = 0
        }
        var index = 0
        return recommendedArtworks.associateWith { recommendationTypesApplied[index++] }
    }

    private companion object {
        const val MAXIMUM_RECOMMENDATIONS = 10
        const val MAXIMUM_ATTEMPTS = 3
    }
}
