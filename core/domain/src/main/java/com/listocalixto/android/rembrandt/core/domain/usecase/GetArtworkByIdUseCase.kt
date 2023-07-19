package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.core.domain.repository.ArtworkRepo
import javax.inject.Inject

class GetArtworkByIdUseCase @Inject constructor(
    private val repo: ArtworkRepo,
) {
    /*suspend operator fun invoke(id: Long): Artwork {
        return repo.getArtworkById(id)
    }*/
}
