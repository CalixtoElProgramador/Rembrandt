package com.listocalixto.android.rembrandt.core.domain.usecase

import javax.inject.Inject

class SetTranslationByArtworkUseCase @Inject constructor(
    private val updateArtwork: UpdateArtworkUseCase,
) {
    /*suspend operator fun invoke(artwork: Artwork, translation: Translation) {
        updateArtwork.invoke(artwork.copy(translation = translation))
    }*/
}
