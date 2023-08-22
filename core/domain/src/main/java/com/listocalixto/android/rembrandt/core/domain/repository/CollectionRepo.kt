package com.listocalixto.android.rembrandt.core.domain.repository

import com.listocalixto.android.rembrandt.common.entities.Collection
import kotlinx.coroutines.flow.Flow

interface CollectionRepo {
    fun observeAllCollections(): Flow<List<Collection>>
    suspend fun getAllCollections(): List<Collection>
    fun observeCollectionById(id: String): Flow<Collection?>
    suspend fun getCollectionById(id: String): Collection?
    suspend fun upsertCollections(collections: List<Collection>)
    suspend fun upsertCollection(collection: Collection)
    suspend fun deleteAllCollections()
    suspend fun deleteCollectionById(id: String)
}
