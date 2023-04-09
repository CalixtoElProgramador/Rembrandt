package com.listocalixto.android.rembrandt.domain.usecase.main

import com.listocalixto.android.rembrandt.domain.entity.Artwork
import com.listocalixto.android.rembrandt.domain.repo.ArtworkRepo
import javax.inject.Inject

class UpdateArtworkUseCase @Inject constructor(
    private val repo: ArtworkRepo
) {

    suspend operator fun invoke(artwork: Artwork) = repo.updateArtwork(artwork)
}
