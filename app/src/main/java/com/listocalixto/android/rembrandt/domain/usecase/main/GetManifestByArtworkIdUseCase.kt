package com.listocalixto.android.rembrandt.domain.usecase.main

import com.listocalixto.android.rembrandt.domain.entity.Manifest
import com.listocalixto.android.rembrandt.domain.repo.ManifestRepo
import javax.inject.Inject

class GetManifestByArtworkIdUseCase @Inject constructor(private val repo: ManifestRepo) {

    suspend operator fun invoke(artworkId: Long, forceUpdate: Boolean = false): Manifest {
        return repo.getManifestByArtworkId(artworkId, forceUpdate)
    }
}
