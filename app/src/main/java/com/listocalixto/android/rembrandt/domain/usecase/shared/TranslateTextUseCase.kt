package com.listocalixto.android.rembrandt.domain.usecase.shared

import com.listocalixto.android.rembrandt.domain.repo.SharedRepo
import javax.inject.Inject

class TranslateTextUseCase @Inject constructor(
    private val repo: SharedRepo
) {

    suspend operator fun invoke(text: String, targetLang: String): String {
        return repo.translateText(text, targetLang)
    }
}
