package com.listocalixto.android.rembrandt.core.domain.repository

interface TranslatorRepo {
    suspend fun translateText(text: String, targetLang: String): String
}
