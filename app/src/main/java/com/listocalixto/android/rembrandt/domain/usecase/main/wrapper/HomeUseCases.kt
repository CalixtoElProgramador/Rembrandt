package com.listocalixto.android.rembrandt.domain.usecase.main.wrapper

import com.listocalixto.android.rembrandt.domain.usecase.main.GetArtworkByIdUseCase
import com.listocalixto.android.rembrandt.domain.usecase.main.GetArtworksByPageUseCase
import com.listocalixto.android.rembrandt.domain.usecase.main.UpdateArtworkUseCase
import javax.inject.Inject

data class HomeUseCases @Inject constructor(
    val getArtworksByPage: GetArtworksByPageUseCase,
    val updateArtwork: UpdateArtworkUseCase,
    val getArtworkById: GetArtworkByIdUseCase
)
