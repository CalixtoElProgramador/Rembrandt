package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.core.domain.repository.ArtworkRepo
import javax.inject.Inject

class UpdateArtworkUseCase @Inject constructor(
    private val repo: ArtworkRepo,
) {
//    suspend operator fun invoke(artwork: Artwork) = repo.cupdateArtwork(artwork)
}
