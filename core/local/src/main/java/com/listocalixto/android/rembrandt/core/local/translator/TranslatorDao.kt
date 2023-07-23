package com.listocalixto.android.rembrandt.core.local.translator

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface TranslatorDao {
    @Query("select * from translations where id = :id")
    suspend fun getTranslationById(id: String): List<LocalTranslation>

    @Upsert
    suspend fun insertTranslation(item: LocalTranslation)

    @Query("delete from translations")
    suspend fun deleteAllTranslations()

    @Query("delete from translations where id = :id")
    suspend fun deleteTranslationById(id: String)
}
