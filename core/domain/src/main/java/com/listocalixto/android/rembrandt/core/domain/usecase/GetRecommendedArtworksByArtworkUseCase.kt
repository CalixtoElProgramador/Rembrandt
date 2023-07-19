package com.listocalixto.android.rembrandt.core.domain.usecase

import javax.inject.Inject

class GetRecommendedArtworksByArtworkUseCase @Inject constructor(
    private val getAllArtworks: GetAllArtworksUseCase,
    private val getRecommendedArtworkByRecommendationType: GetRecommendedArtworkByRecommendationTypeUseCase,
    private val generateRecommendationTypesByArtwork: GetRecommendationTypesByArtworkUseCase,
) {

    /*suspend operator fun invoke(currentArtwork: Artwork): GetRecommendedArtworksByArtworkResult {
        val artworks = getAllArtworks()
        val recommendations = mutableListOf<Artwork>()
        val recommendationTypes = generateRecommendationTypesByArtwork(currentArtwork)
        val recommendationTypesApplied = mutableListOf<RecommendationType>()
        var totalRecommendations = 0
        var attemptsUsed = 0
        while (totalRecommendations < MAXIMUM_RECOMMENDATIONS) {
            if (attemptsUsed == MAXIMUM_ATTEMPTS) break
            val randomType = recommendationTypes.random()
            val artworkRecommended =
                getRecommendedArtworkByRecommendationType(randomType, artworks.toList())
            if (artworkRecommended == null) {
                attemptsUsed++
                continue
            }
            val alreadyAdded = recommendations.any { artworkRecommended.id == it.id }
            val isTheSameAsTheCurrentArtwork =
                currentArtwork.hashCode() == artworkRecommended.hashCode()
            if (alreadyAdded || isTheSameAsTheCurrentArtwork) {
                attemptsUsed++
                continue
            }
            recommendations.add(artworkRecommended)
            recommendationTypesApplied.add(randomType)
            totalRecommendations = recommendations.size
            attemptsUsed = 0
        }
        return GetRecommendedArtworksByArtworkResult(recommendations, recommendationTypesApplied)
    }*/

    companion object {
        private const val MAXIMUM_RECOMMENDATIONS = 10
        private const val MAXIMUM_ATTEMPTS = 3
    }
}