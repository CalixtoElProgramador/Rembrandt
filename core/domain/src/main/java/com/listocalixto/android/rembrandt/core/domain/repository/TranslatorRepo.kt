package com.listocalixto.android.rembrandt.core.domain.repository

import com.listocalixto.android.rembrandt.common.entities.Translation

interface TranslatorRepo {
    suspend fun translateText(text: String, targetLanguage: String): String
    suspend fun getTranslationById(id: String): Translation?
    suspend fun getTranslations(
        id: String,
        keysAndRequests: Map<String, String?>,
        targetLanguage: String,
    ): Translation
}
