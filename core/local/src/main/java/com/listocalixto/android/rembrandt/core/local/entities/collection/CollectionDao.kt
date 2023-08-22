package com.listocalixto.android.rembrandt.core.local.entities.collection

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectionDao {
    @Query("SELECT * FROM collections")
    fun observeAllCollections(): Flow<List<LocalCollection>>

    @Query("SELECT * FROM collections")
    suspend fun getAllCollections(): List<LocalCollection>

    @Query("SELECT * FROM collections WHERE id = :id")
    fun observeCollectionsById(id: String): Flow<List<LocalCollection>>

    @Query("SELECT * FROM collections WHERE id = :id")
    suspend fun getCollectionsById(id: String): List<LocalCollection>

    @Upsert
    suspend fun upsertCollection(userCollection: LocalCollection)

    @Query("DELETE FROM collections")
    suspend fun deleteAllCollections()

    @Query("DELETE FROM collections WHERE id = :id")
    suspend fun deleteCollectionById(id: String)
}
