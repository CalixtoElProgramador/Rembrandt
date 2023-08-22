package com.listocalixto.android.rembrandt.data.collection

import com.listocalixto.android.rembrandt.common.dependencies.di.ApplicationScope
import com.listocalixto.android.rembrandt.common.dependencies.di.Dispatcher
import com.listocalixto.android.rembrandt.common.dependencies.di.RDispatchers.IO
import com.listocalixto.android.rembrandt.common.entities.Collection
import com.listocalixto.android.rembrandt.core.domain.repository.CollectionRepo
import com.listocalixto.android.rembrandt.data.collection.local.LocalCollectionDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class DefaultCollectionRepo @Inject constructor(
    private val localDataSource: LocalCollectionDataSource,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    @ApplicationScope private val appScope: CoroutineScope,
) : CollectionRepo {
    override fun observeAllCollections(): Flow<List<Collection>> {
        return localDataSource.observeAllCollections()
    }

    override suspend fun getAllCollections(): List<Collection> {
        return withContext(ioDispatcher) {
            localDataSource.getAllCollections()
        }
    }

    override fun observeCollectionById(id: String): Flow<Collection?> {
        return localDataSource.observeUserCollectionById(id)
    }

    override suspend fun getCollectionById(id: String): Collection? {
        return withContext(ioDispatcher) {
            localDataSource.getUserCollectionById(id)
        }
    }

    override suspend fun upsertCollections(collections: List<Collection>) {
        appScope.launch(appScope.coroutineContext + ioDispatcher) {
            localDataSource.upsertUserCollections(collections)
        }
    }

    override suspend fun upsertCollection(collection: Collection) {
        appScope.launch(appScope.coroutineContext + ioDispatcher) {
            localDataSource.upsertUserCollection(collection)
        }
    }

    override suspend fun deleteAllCollections() {
        withContext(ioDispatcher) {
            localDataSource.deleteAllUserCollections()
        }
    }

    override suspend fun deleteCollectionById(id: String) {
        withContext(ioDispatcher) {
            localDataSource.deleteCollectionById(id)
        }
    }
}
