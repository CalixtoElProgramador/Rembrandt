package com.listocalixto.android.rembrandt.core.domain.usecase

import javax.inject.Inject

class GetManifestIdByArtworkIdUseCase @Inject constructor() {

    operator fun invoke(artworkId: Long): String {
        return "https://api.artic.edu/api/v1/artworks/$artworkId/manifest.json"
    }
}
