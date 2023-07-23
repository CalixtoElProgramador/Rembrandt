package com.listocalixto.android.rembrandt.data.manifest

import com.listocalixto.android.rembrandt.common.dependencies.di.Dispatcher
import com.listocalixto.android.rembrandt.common.dependencies.di.RDispatchers.IO
import com.listocalixto.android.rembrandt.common.entities.Manifest
import com.listocalixto.android.rembrandt.core.domain.repository.ManifestRepo
import com.listocalixto.android.rembrandt.data.manifest.local.LocalManifestDataSource
import com.listocalixto.android.rembrandt.data.manifest.remote.RemoteManifestDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class ManifestRepoImpl @Inject constructor(
    private val localSource: LocalManifestDataSource,
    private val remoteSource: RemoteManifestDataSource,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) : ManifestRepo {

    override suspend fun getManifestByArtworkId(id: Long): Manifest {
        return localSource.getManifestById(id.toString()) ?: withContext(ioDispatcher) {
            remoteSource.getManifestByArtworkId(id).also {
                localSource.insertManifest(it)
            }
        }
    }

    override suspend fun deleteAllManifests() {
        withContext(ioDispatcher) {
            localSource.deleteAllManifests()
        }
    }
}
