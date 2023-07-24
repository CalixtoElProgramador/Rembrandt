package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.common.entities.Translation
import com.listocalixto.android.rembrandt.core.domain.utility.TranslationFromType.Artwork
import javax.inject.Inject

class GetTranslationByArtworkIdUseCase @Inject constructor(
    private val getTranslationId: GetTranslationIdUseCase,
    private val getTranslationById: GetTranslationByIdUseCase,
) {

    suspend operator fun invoke(id: Long): Translation? {
        val translationId = getTranslationId(itemId = id, from = Artwork)
        return getTranslationById(translationId)
    }
}
