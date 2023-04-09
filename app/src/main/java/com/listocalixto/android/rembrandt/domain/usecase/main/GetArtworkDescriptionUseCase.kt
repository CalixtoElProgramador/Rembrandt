package com.listocalixto.android.rembrandt.domain.usecase.main

import javax.inject.Inject

class GetArtworkDescriptionUseCase @Inject constructor() {

    operator fun invoke(manifestDescription: String, artworkAltText: String): String {
        return manifestDescription.ifEmpty { artworkAltText.ifEmpty { NO_DESCRIPTION } }
    }

    companion object {
        const val NO_DESCRIPTION = "Description not available."
    }
}
