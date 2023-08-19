package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.common.entities.Translation
import com.listocalixto.android.rembrandt.common.entities.Translation.TargetLanguageNotAvailableException
import com.listocalixto.android.rembrandt.core.domain.repository.TranslatorRepo
import com.listocalixto.android.rembrandt.core.domain.utility.TranslationFromType
import javax.inject.Inject

class GetTranslationsUseCase @Inject constructor(
    private val repo: TranslatorRepo,
    private val getTranslationId: GetTranslationIdUseCase,
    private val getUserTargetLanguage: GetUserTargetLanguageUseCase,
) {
    @Throws(TargetLanguageNotAvailableException::class)
    suspend operator fun <T : Enum<T>> invoke(
        itemId: Long,
        fromType: TranslationFromType,
        keysAndRequests: Map<String, String>,
    ): Translation {
        val id = getTranslationId(itemId, fromType)
        return repo.getTranslations(id, keysAndRequests, getUserTargetLanguage())
    }
}
