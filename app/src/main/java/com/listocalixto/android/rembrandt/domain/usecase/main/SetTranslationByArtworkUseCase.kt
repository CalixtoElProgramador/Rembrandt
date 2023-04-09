package com.listocalixto.android.rembrandt.domain.usecase.main

import com.listocalixto.android.rembrandt.domain.entity.Artwork
import com.listocalixto.android.rembrandt.domain.model.Translation
import javax.inject.Inject

class SetTranslationByArtworkUseCase @Inject constructor(
    private val updateArtwork: UpdateArtworkUseCase
) {

    suspend operator fun invoke(artwork: Artwork, translation: Translation) {
        updateArtwork.invoke(artwork.copy(translation = translation))
    }
}
