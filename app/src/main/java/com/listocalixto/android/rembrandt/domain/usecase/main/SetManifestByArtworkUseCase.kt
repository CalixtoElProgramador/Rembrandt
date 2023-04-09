package com.listocalixto.android.rembrandt.domain.usecase.main

import com.listocalixto.android.rembrandt.domain.entity.Artwork
import com.listocalixto.android.rembrandt.domain.model.Manifest
import javax.inject.Inject

class SetManifestByArtworkUseCase @Inject constructor(
    private val updateArtworkUseCase: UpdateArtworkUseCase
) {

    suspend operator fun invoke(artwork: Artwork, manifest: Manifest) {
        updateArtworkUseCase(artwork.copy(manifest = manifest))
    }
}
