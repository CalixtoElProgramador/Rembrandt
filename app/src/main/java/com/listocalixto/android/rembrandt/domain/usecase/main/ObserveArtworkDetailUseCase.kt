package com.listocalixto.android.rembrandt.domain.usecase.main

import com.listocalixto.android.rembrandt.domain.entity.Artwork
import com.listocalixto.android.rembrandt.domain.result.GetArtworkDetailResult
import com.listocalixto.android.rembrandt.domain.utility.RecommendationType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ObserveArtworkDetailUseCase @Inject constructor(
    private val getAllArtworks: GetAllArtworksUseCase,
    private val observeArtworkById: ObserveArtworkByIdUseCase,
    private val getRecommendedArtworkByRecommendationType: GetRecommendedArtworkByRecommendationTypeUseCase,
    private val generateRecommendationTypesByArtwork: GenerateRecommendationTypesByArtworkUseCase,
) {

    operator fun invoke(artworkId: Long): Flow<Result<GetArtworkDetailResult>> = flow {
        observeArtworkById(artworkId).collect { result ->
            result.onSuccess { currentArtwork ->
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
                val useCaseResult = GetArtworkDetailResult(
                    currentArtwork,
                    recommendations,
                    recommendationTypesApplied,
                )
                emit(Result.success(useCaseResult))
            }.onFailure {
                emit(Result.failure(it))
            }
        }
    }

    companion object {
        private const val MAXIMUM_RECOMMENDATIONS = 10
        private const val MAXIMUM_ATTEMPTS = 3
    }
}
