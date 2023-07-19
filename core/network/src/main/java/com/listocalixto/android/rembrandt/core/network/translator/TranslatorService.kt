package com.listocalixto.android.rembrandt.core.network.translator

interface TranslatorService {
    suspend fun translateText(text: String, targetLang: String): String
}
