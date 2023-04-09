package com.listocalixto.android.rembrandt.domain.usecase.main

import com.listocalixto.android.rembrandt.core.Constants.EMPTY
import com.listocalixto.android.rembrandt.domain.entity.Artwork
import com.listocalixto.android.rembrandt.domain.model.Translation
import com.listocalixto.android.rembrandt.domain.repo.SharedRepo
import javax.inject.Inject

class SetTranslationByArtworkUseCase @Inject constructor(
    private val sharedRepo: SharedRepo,
    private val updateArtwork: UpdateArtworkUseCase
) {

    suspend operator fun invoke(artwork: Artwork, targetLang: String = TARGET_LANG_DEFAULT_VALUE) {
        val textsToTranslated = listOf(
            artwork.categoryTitles.first(),
            artwork.title,
            artwork.manifest?.description ?: EMPTY
        )
        val textsTranslated = textsToTranslated.map { sharedRepo.translateText(it, targetLang) }
        val translation = Translation(
            category = textsTranslated[0],
            title = textsTranslated[1],
            content = textsTranslated[2]
        )
        updateArtwork.invoke(artwork.copy(translation = translation))
    }

    companion object {
        private const val TARGET_LANG_DEFAULT_VALUE = "ES"
    }
}
