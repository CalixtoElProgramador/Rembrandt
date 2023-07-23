package com.listocalixto.android.rembrandt.data.translator.remote

internal interface RemoteTranslatorDataSource {
    suspend fun translateText(text: String, targetLanguage: String): String
}
