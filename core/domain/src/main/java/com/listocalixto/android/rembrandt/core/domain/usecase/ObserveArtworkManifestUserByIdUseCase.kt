package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.common.entities.composite.ArtworkManifestTranslationUser
import com.listocalixto.android.rembrandt.core.domain.repository.ManifestRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ObserveArtworkManifestUserByIdUseCase @Inject constructor(
    private val observeArtworkUserById: ObserveArtworkUserByIdUseCase,
    private val manifestRepo: ManifestRepo,
) {

    operator fun invoke(artworkId: Long): Flow<ArtworkManifestTranslationUser> {
        return observeArtworkUserById(artworkId).combine(
            flow { emit(manifestRepo.getManifestByArtworkId(artworkId)) },
        ) { artworkUser, manifest ->
            ArtworkManifestTranslationUser(artworkUser, manifest, null)
        }
    }
}
