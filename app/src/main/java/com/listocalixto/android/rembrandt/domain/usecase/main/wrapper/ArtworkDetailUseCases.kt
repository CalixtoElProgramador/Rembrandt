package com.listocalixto.android.rembrandt.domain.usecase.main.wrapper

import com.listocalixto.android.rembrandt.domain.usecase.main.GetArtworkDescriptionUseCase
import com.listocalixto.android.rembrandt.domain.usecase.main.GetImageUrlUseCase
import com.listocalixto.android.rembrandt.domain.usecase.main.GetManifestByArtworkIdUseCase
import com.listocalixto.android.rembrandt.domain.usecase.main.GetRecommendedArtworksByArtworkUseCase
import com.listocalixto.android.rembrandt.domain.usecase.main.GetTranslationByArtworkUseCase
import com.listocalixto.android.rembrandt.domain.usecase.main.ObserveArtworkByIdUseCase
import com.listocalixto.android.rembrandt.domain.usecase.main.ObserveArtworkWithManifestUseCase
import com.listocalixto.android.rembrandt.domain.usecase.main.SetTranslationByArtworkUseCase
import com.listocalixto.android.rembrandt.domain.usecase.main.UpdateArtworkUseCase
import javax.inject.Inject

data class ArtworkDetailUseCases @Inject constructor(
    val observeArtworkWithManifest: ObserveArtworkWithManifestUseCase,
    val getManifestByArtworkId: GetManifestByArtworkIdUseCase,
    val observeArtworkById: ObserveArtworkByIdUseCase,
    val getRecommendedArtworksByArtwork: GetRecommendedArtworksByArtworkUseCase,
    val updateArtwork: UpdateArtworkUseCase,
    val getArtworkDescription: GetArtworkDescriptionUseCase,
    val setTranslationByArtwork: SetTranslationByArtworkUseCase,
    val getTranslationByArtwork: GetTranslationByArtworkUseCase,
    val getImageUrlUseCase: GetImageUrlUseCase,
)
