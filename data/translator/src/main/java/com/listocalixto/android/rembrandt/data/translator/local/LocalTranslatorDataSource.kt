package com.listocalixto.android.rembrandt.data.translator.local

import com.listocalixto.android.rembrandt.common.entities.Translation

internal interface LocalTranslatorDataSource {
    suspend fun getTranslationById(id: String): Translation?
    suspend fun insertTranslation(item: Translation)
    suspend fun deleteAllTranslations()
    suspend fun deleteTranslationById(id: String)
}
