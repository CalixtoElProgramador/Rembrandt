package com.listocalixto.android.rembrandt.domain.usecase.main

data class HomeUseCases(
    val getArtworksByPage: GetArtworksByPageUseCase,
    val updateArtwork: UpdateArtworkUseCase,
    val getArtworkById: GetArtworkByIdUseCase,
)
