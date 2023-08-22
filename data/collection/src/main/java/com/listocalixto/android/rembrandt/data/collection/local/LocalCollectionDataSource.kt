package com.listocalixto.android.rembrandt.data.collection.local

import com.listocalixto.android.rembrandt.common.entities.Collection
import kotlinx.coroutines.flow.Flow

internal interface LocalCollectionDataSource {
    fun observeAllCollections(): Flow<List<Collection>>
    suspend fun getAllCollections(): List<Collection>
    fun observeUserCollectionById(id: String): Flow<Collection?>
    suspend fun getUserCollectionById(id: String): Collection?
    suspend fun upsertUserCollections(collections: List<Collection>)
    suspend fun upsertUserCollection(collection: Collection)
    suspend fun deleteAllUserCollections()
    suspend fun deleteCollectionById(id: String)
}
