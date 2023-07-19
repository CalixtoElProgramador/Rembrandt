package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.core.domain.repository.ArtworkRepo
import javax.inject.Inject

class GetManifestByArtworkIdUseCase @Inject constructor(private val repo: ArtworkRepo) {
    /*suspend operator fun invoke(artworkId: Long): Manifest {
        return repo.fetchManifestByArtworkId(artworkId)
    }*/
}
