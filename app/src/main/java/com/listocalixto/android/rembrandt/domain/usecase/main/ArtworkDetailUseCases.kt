package com.listocalixto.android.rembrandt.domain.usecase.main

data class ArtworkDetailUseCases(
    val observeArtworkById: ObserveArtworkByIdUseCase,
    val getRecommendedArtworksByArtwork: GetRecommendedArtworksByArtworkUseCase,
    val updateArtwork: UpdateArtworkUseCase,
)
