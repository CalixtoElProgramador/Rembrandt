package com.listocalixto.android.rembrandt.data.manifest.remote

import com.listocalixto.android.rembrandt.common.dependencies.di.Dispatcher
import com.listocalixto.android.rembrandt.common.dependencies.di.RDispatchers.Default
import com.listocalixto.android.rembrandt.common.entities.Manifest
import com.listocalixto.android.rembrandt.core.network.manifest.ManifestService
import com.listocalixto.android.rembrandt.data.manifest.mapper.RemoteManifestToEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RemoteManifestDataSourceImpl @Inject constructor(
    private val service: ManifestService,
    private val mapper: RemoteManifestToEntity,
    @Dispatcher(Default) private val defaultDispatcher: CoroutineDispatcher,
) : RemoteManifestDataSource {

    override suspend fun getManifestByArtworkId(id: Long): Manifest {
        val response = service.getManifestByArtworkId(id)
        return withContext(defaultDispatcher) {
            mapper.map(response)
        }
    }
}
