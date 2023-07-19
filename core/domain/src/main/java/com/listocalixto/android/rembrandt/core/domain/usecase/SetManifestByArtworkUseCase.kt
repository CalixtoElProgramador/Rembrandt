package com.listocalixto.android.rembrandt.core.domain.usecase

import javax.inject.Inject

class SetManifestByArtworkUseCase @Inject constructor(
    private val updateArtworkUseCase: UpdateArtworkUseCase,
) {
    /*suspend operator fun invoke(artwork: Artwork, manifest: Manifest) {
        updateArtworkUseCase(artwork.copy(manifest = manifest))
    }*/
}
