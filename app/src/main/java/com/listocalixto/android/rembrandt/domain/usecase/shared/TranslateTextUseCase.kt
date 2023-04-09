package com.listocalixto.android.rembrandt.domain.usecase.shared

import com.listocalixto.android.rembrandt.domain.model.Translation.Exception.TargetLanguageNotAvailable
import com.listocalixto.android.rembrandt.domain.repo.SharedRepo
import javax.inject.Inject

class TranslateTextUseCase @Inject constructor(
    private val repo: SharedRepo
) {

    @Throws(TargetLanguageNotAvailable::class)
    suspend operator fun invoke(text: String, targetLang: String): String {
        val targetLangUpperCase = targetLang.uppercase()
        val translationsAvailable = setOf(
            "BG",
            "CS",
            "DA",
            "DE",
            "EL",
            "EN",
            "EN-GB",
            "EN-US",
            "ES",
            "ET",
            "FI",
            "FR",
            "HU",
            "ID",
            "IT",
            "JA",
            "KO",
            "LT",
            "LV",
            "NB",
            "NL",
            "PL",
            "PT",
            "PT-BR",
            "PT-PT",
            "RO",
            "RU",
            "SK",
            "SL",
            "SV",
            "TR",
            "UK",
            "ZH"
        )
        translationsAvailable.find { it == targetLangUpperCase }?.let {
            return repo.translateText(text, targetLangUpperCase)
        } ?: run {
            val language = targetLangUpperCase.split("-")[0]
            translationsAvailable.find { it == language }?.let {
                return repo.translateText(text, language)
            } ?: run {
                throw TargetLanguageNotAvailable
            }
        }
    }
}
