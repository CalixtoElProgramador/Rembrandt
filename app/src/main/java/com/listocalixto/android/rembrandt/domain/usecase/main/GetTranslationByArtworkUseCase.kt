package com.listocalixto.android.rembrandt.domain.usecase.main

import com.listocalixto.android.rembrandt.core.Constants.EMPTY
import com.listocalixto.android.rembrandt.domain.entity.Artwork
import com.listocalixto.android.rembrandt.domain.model.Translation
import com.listocalixto.android.rembrandt.domain.usecase.shared.TranslateTextUseCase
import javax.inject.Inject

class GetTranslationByArtworkUseCase @Inject constructor(
    private val translateText: TranslateTextUseCase
) {

    suspend operator fun invoke(
        artwork: Artwork,
        targetLang: String = TARGET_LANG_DEFAULT_VALUE
    ): Translation {
        val translation = Translation(
            category = artwork.categoryTitles.first(),
            title = artwork.title,
            content = artwork.manifest?.description ?: EMPTY
        )
        return translation.copy(
            category = translateText(translation.category, targetLang),
            title = translateText(translation.title, targetLang),
            content = translateText(translation.content, targetLang)
        )
    }

    companion object {
        private const val TARGET_LANG_DEFAULT_VALUE = "ES"
    }
}
