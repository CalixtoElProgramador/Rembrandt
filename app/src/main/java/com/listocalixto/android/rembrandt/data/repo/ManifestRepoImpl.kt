package com.listocalixto.android.rembrandt.data.repo

import com.listocalixto.android.rembrandt.data.source.local.implementation.LocalManifestDataSource
import com.listocalixto.android.rembrandt.data.source.remote.implementation.RemoteManifestDataSource
import com.listocalixto.android.rembrandt.domain.entity.Manifest
import com.listocalixto.android.rembrandt.domain.repo.ManifestRepo
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ManifestRepoImpl @Inject constructor(
    private val remoteDataSource: RemoteManifestDataSource,
    private val localDataSource: LocalManifestDataSource
) : ManifestRepo {

    override suspend fun getManifestByArtworkId(id: Long, forceUpdate: Boolean): Manifest =
        withContext(Dispatchers.IO) {
            val localManifest = localDataSource.getManifestByArtworkId(id)
            return@withContext if (localManifest == null || forceUpdate) {
                val remoteManifest = remoteDataSource.fetchManifestByArtworkId(id)
                localDataSource.insertManifest(remoteManifest)
                localDataSource.getManifestByArtworkId(id) ?: throw Exception()
            } else {
                localManifest
            }
        }
}
