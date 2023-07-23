package com.listocalixto.android.rembrandt.core.domain.usecase

import javax.inject.Inject

class GetTranslationByArtworkUseCase @Inject constructor(
    private val translateText: GetTranslationById,
) {
    /*suspend operator fun invoke(
        artwork: Artwork,
    ): Translation {
        val translation = Translation(
            category = artwork.categoryTitles.first(),
            title = artwork.title,
            content = artwork.manifest?.description ?: EMPTY,
        )
        return translation.copy(
            category = translateText(translation.category),
            title = translateText(translation.title),
            content = translateText(translation.content),
        )
    }*/
}
