package com.listocalixto.android.rembrandt.domain.usecase.main

import javax.inject.Inject

data class ArtworkDetailUseCases @Inject constructor(
    val observeArtworkWithManifest: ObserveArtworkWithManifestUseCase,
    val getManifestByArtworkId: GetManifestByArtworkIdUseCase,
    val observeArtworkById: ObserveArtworkByIdUseCase,
    val getRecommendedArtworksByArtwork: GetRecommendedArtworksByArtworkUseCase,
    val updateArtwork: UpdateArtworkUseCase,
    val getArtworkDescription: GetArtworkDescriptionUseCase,
    val setTranslationByArtwork: SetTranslationByArtworkUseCase,
    val getTranslationByArtwork: GetTranslationByArtworkUseCase
)
