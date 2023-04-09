package com.listocalixto.android.rembrandt.domain.usecase.main

import com.listocalixto.android.rembrandt.domain.entity.Artwork
import javax.inject.Inject

class SetManifestByArtworkUseCase @Inject constructor(
    private val getManifestByArtworkId: GetManifestByArtworkIdUseCase,
    private val updateArtworkUseCase: UpdateArtworkUseCase
) {

    suspend operator fun invoke(artwork: Artwork) {
        val manifest = getManifestByArtworkId(artwork.id)
        updateArtworkUseCase(artwork.copy(manifest = manifest))
    }
}
