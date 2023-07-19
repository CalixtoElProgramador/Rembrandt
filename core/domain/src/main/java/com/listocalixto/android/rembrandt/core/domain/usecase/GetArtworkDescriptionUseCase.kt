package com.listocalixto.android.rembrandt.core.domain.usecase

import javax.inject.Inject

class GetArtworkDescriptionUseCase @Inject constructor() {
    operator fun invoke(manifestDescription: String, artworkAltText: String): String {
        return manifestDescription.ifEmpty { artworkAltText.ifEmpty { NO_DESCRIPTION } }
    }

    companion object {
        const val NO_DESCRIPTION = "Description not available."
    }
}
