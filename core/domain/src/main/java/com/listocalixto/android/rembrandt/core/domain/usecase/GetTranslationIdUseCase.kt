package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.core.domain.utility.TranslationFromType
import javax.inject.Inject

class GetTranslationIdUseCase @Inject constructor(
    private val getUserTargetLanguage: GetUserTargetLanguageUseCase,
) {
    operator fun invoke(itemId: Long, from: TranslationFromType): String {
        return "${from.name}-$itemId-${getUserTargetLanguage()}"
    }
}
