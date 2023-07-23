package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.core.domain.utility.TranslationFromType
import javax.inject.Inject

class GetTranslationIdUseCase @Inject constructor() {

    operator fun invoke(itemId: Long, from: TranslationFromType, targetLanguage: String): String {
        return "${from.name}-$itemId-$targetLanguage"
    }
}
