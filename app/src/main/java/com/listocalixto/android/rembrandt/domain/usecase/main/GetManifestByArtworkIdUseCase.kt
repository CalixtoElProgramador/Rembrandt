package com.listocalixto.android.rembrandt.domain.usecase.main

import com.listocalixto.android.rembrandt.domain.model.Manifest
import com.listocalixto.android.rembrandt.domain.repo.ArtworkRepo
import javax.inject.Inject

class GetManifestByArtworkIdUseCase @Inject constructor(private val repo: ArtworkRepo) {
    suspend operator fun invoke(artworkId: Long): Manifest {
        return repo.fetchManifestByArtworkId(artworkId)
    }
}
