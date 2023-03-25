package com.listocalixto.android.rembrandt.domain.usecase.main

import com.listocalixto.android.rembrandt.domain.entity.Artwork
import com.listocalixto.android.rembrandt.domain.repo.ArtworkRepo

class GetArtworkByIdUseCase(
    private val repo: ArtworkRepo,
) {

    suspend operator fun invoke(id: Long): Artwork {
        return repo.getArtworkById(id)
    }
}
