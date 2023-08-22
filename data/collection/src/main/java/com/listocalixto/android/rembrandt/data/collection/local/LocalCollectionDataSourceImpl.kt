package com.listocalixto.android.rembrandt.data.collection.local

import com.listocalixto.android.rembrandt.common.dependencies.di.Dispatcher
import com.listocalixto.android.rembrandt.common.dependencies.di.RDispatchers.Default
import com.listocalixto.android.rembrandt.common.entities.Collection
import com.listocalixto.android.rembrandt.core.local.entities.collection.CollectionDao
import com.listocalixto.android.rembrandt.data.collection.mapper.LocalCollectionToEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class LocalCollectionDataSourceImpl @Inject constructor(
    private val dao: CollectionDao,
    private val mapper: LocalCollectionToEntity,
    @Dispatcher(Default) private val defaultDispatcher: CoroutineDispatcher,
) : LocalCollectionDataSource {
    override fun observeAllCollections(): Flow<List<Collection>> {
        return dao.observeAllCollections().map { mapper.map(it) }.flowOn(defaultDispatcher)
    }

    override suspend fun getAllCollections(): List<Collection> {
        return withContext(defaultDispatcher) {
            dao.getAllCollections().map { mapper.map(it) }
        }
    }

    override fun observeUserCollectionById(id: String): Flow<Collection?> {
        val localUserCollection = dao.observeCollectionsById(id)
        return localUserCollection.map { result ->
            result.firstOrNull()?.let { mapper.map(it) }
        }
    }

    override suspend fun getUserCollectionById(id: String): Collection? {
        val localUserCollection = dao.getCollectionsById(id)
        return localUserCollection.firstOrNull()?.let {
            mapper.map(it)
        }
    }

    override suspend fun upsertUserCollections(collections: List<Collection>) {
        withContext(defaultDispatcher) {
            collections.forEach { upsertUserCollection(it) }
        }
    }

    override suspend fun upsertUserCollection(collection: Collection) {
        dao.upsertCollection(mapper.reverseMap(collection))
    }

    override suspend fun deleteAllUserCollections() {
        dao.deleteAllCollections()
    }

    override suspend fun deleteCollectionById(id: String) {
        dao.deleteCollectionById(id)
    }
}
