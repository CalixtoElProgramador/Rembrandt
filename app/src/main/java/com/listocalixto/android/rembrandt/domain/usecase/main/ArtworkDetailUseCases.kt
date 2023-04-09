package com.listocalixto.android.rembrandt.domain.usecase.main

import javax.inject.Inject

data class ArtworkDetailUseCases @Inject constructor(
    val getManifestByArtworkId: GetManifestByArtworkIdUseCase,
    val observeArtworkById: ObserveArtworkByIdUseCase,
    val getRecommendedArtworksByArtwork: GetRecommendedArtworksByArtworkUseCase,
    val updateArtwork: UpdateArtworkUseCase,
    val getArtworkDescription: GetArtworkDescriptionUseCase
)
