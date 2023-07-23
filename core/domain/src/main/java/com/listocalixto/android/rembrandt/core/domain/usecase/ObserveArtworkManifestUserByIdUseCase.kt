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
    private val getManifestIdByArtworkId: GetManifestIdByArtworkIdUseCase,
) {

    operator fun invoke(artworkId: Long): Flow<ArtworkManifestTranslationUser> {
        val manifestId: String = getManifestIdByArtworkId(artworkId)
        return observeArtworkUserById(artworkId).combine(
            flow { emit(manifestRepo.getManifestByArtworkId(artworkId, manifestId)) },
        ) { artworkUser, manifest ->
            ArtworkManifestTranslationUser(artworkUser, manifest, null)
        }
    }
}
