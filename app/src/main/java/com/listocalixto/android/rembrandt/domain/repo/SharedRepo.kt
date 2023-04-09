package com.listocalixto.android.rembrandt.domain.repo

interface SharedRepo {

    suspend fun translateText(text: String, targetLang: String): String
}
