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
        artwork: Artwork
    ): Translation {
        val translation = Translation(
            category = artwork.categoryTitles.first(),
            title = artwork.title,
            content = artwork.manifest?.description ?: EMPTY
        )
        return translation.copy(
            category = translateText(translation.category),
            title = translateText(translation.title),
            content = translateText(translation.content)
        )
    }
}
