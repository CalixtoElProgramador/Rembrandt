package com.listocalixto.android.rembrandt.domain.usecase.shared

import com.listocalixto.android.rembrandt.domain.model.Translation.Exception.TargetLanguageNotAvailable
import com.listocalixto.android.rembrandt.domain.repo.SharedRepo
import java.util.Locale
import javax.inject.Inject

class TranslateTextUseCase @Inject constructor(
    private val repo: SharedRepo
) {

    @Throws(TargetLanguageNotAvailable::class)
    suspend operator fun invoke(text: String): String {
        val currentLanguage = Locale.getDefault().language.uppercase()
        val currentCountry = Locale.getDefault().country.uppercase()
        val targetLang = "$currentLanguage-$currentCountry"
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
        translationsAvailable.find { it == targetLang }?.let {
            return repo.translateText(text, targetLang)
        } ?: run {
            translationsAvailable.find { it == currentLanguage }?.let {
                return repo.translateText(text, currentLanguage)
            } ?: run {
                throw TargetLanguageNotAvailable
            }
        }
    }
}
