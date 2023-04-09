package com.listocalixto.android.rembrandt.domain.usecase.main

import javax.inject.Inject

data class HomeUseCases @Inject constructor(
    val getArtworksByPage: GetArtworksByPageUseCase,
    val updateArtwork: UpdateArtworkUseCase,
    val getArtworkById: GetArtworkByIdUseCase
)
