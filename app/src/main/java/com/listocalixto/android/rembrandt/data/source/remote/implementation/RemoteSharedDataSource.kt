package com.listocalixto.android.rembrandt.data.source.remote.implementation

interface RemoteSharedDataSource {

    suspend fun translateText(text: String, targetLang: String): String
}
