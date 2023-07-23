package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.common.entities.Manifest
import com.listocalixto.android.rembrandt.core.domain.repository.ManifestRepo
import javax.inject.Inject

class GetManifestByArtworkIdUseCase @Inject constructor(
    private val manifestRepo: ManifestRepo,
) {
    suspend operator fun invoke(id: Long): Manifest {
        return manifestRepo.getManifestByArtworkId(id)
    }
}
