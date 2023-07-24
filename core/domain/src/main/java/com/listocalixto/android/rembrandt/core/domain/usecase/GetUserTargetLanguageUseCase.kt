package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.common.entities.Translation.TargetLanguageNotAvailableException
import java.util.Locale
import javax.inject.Inject

class GetUserTargetLanguageUseCase @Inject constructor() {

    @Throws(TargetLanguageNotAvailableException::class)
    operator fun invoke(): String {
        val currentLanguage = Locale.getDefault().language.uppercase()
        val currentCountry = Locale.getDefault().country.uppercase()
        val languageWithCountry = "$currentLanguage-$currentCountry"
        val matches = translationsAvailable.filter { it.contains(currentLanguage) }
        if (matches.isNotEmpty()) {
            return if (matches.find { it == languageWithCountry } != null) {
                languageWithCountry
            } else {
                currentLanguage
            }
        } else {
            throw TargetLanguageNotAvailableException
        }
    }
}

private val translationsAvailable = setOf(
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
    "ZH",
)
