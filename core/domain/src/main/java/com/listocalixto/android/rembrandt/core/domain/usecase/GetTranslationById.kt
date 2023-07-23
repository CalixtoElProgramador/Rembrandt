package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.common.entities.Translation
import com.listocalixto.android.rembrandt.common.entities.Translation.TargetLanguageNotAvailableException
import com.listocalixto.android.rembrandt.core.domain.repository.TranslatorRepo
import com.listocalixto.android.rembrandt.core.domain.utility.TranslationFromType
import java.util.Locale
import javax.inject.Inject

class GetTranslationById @Inject constructor(
    private val repo: TranslatorRepo,
    private val getTranslationId: GetTranslationIdUseCase,
) {
    @Throws(TargetLanguageNotAvailableException::class)
    suspend operator fun invoke(
        itemId: Long,
        fromType: TranslationFromType,
        keysAndRequests: Set<Pair<String, String>>,
    ): Translation {
        val currentLanguage = Locale.getDefault().language.uppercase()
        val currentCountry = Locale.getDefault().country.uppercase()
        val targetLanguage = "$currentLanguage-$currentCountry"
        translationsAvailable.find { it == targetLanguage }?.let {
            val id = getTranslationId(itemId, fromType, targetLanguage)
            return repo.getTranslations(id, keysAndRequests, targetLanguage)
        } ?: run {
            translationsAvailable.find { it == currentLanguage }?.let {
                val id = getTranslationId(itemId, fromType, currentLanguage)
                return repo.getTranslations(id, keysAndRequests, currentLanguage)
            } ?: run {
                throw TargetLanguageNotAvailableException
            }
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
