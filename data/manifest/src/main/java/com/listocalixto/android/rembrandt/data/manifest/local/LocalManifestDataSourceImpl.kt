package com.listocalixto.android.rembrandt.data.manifest.local

import com.listocalixto.android.rembrandt.common.dependencies.di.Dispatcher
import com.listocalixto.android.rembrandt.common.dependencies.di.RDispatchers.Default
import com.listocalixto.android.rembrandt.common.entities.Manifest
import com.listocalixto.android.rembrandt.core.local.manifest.ManifestDao
import com.listocalixto.android.rembrandt.data.manifest.mapper.LocalManifestToEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class LocalManifestDataSourceImpl @Inject constructor(
    private val dao: ManifestDao,
    private val mapper: LocalManifestToEntity,
    @Dispatcher(Default) private val defaultDispatcher: CoroutineDispatcher,
) : LocalManifestDataSource {
    override suspend fun getManifestById(id: String): Manifest? {
        val localManifest = dao.getManifestById(id)
        return if (localManifest.isNotEmpty()) {
            withContext(defaultDispatcher) {
                mapper.map(localManifest.first())
            }
        } else {
            null
        }
    }

    override suspend fun insertManifest(manifest: Manifest) {
        val localManifest = mapper.reverseMap(manifest)
        dao.insertManifest(localManifest)
    }

    override suspend fun deleteAllManifests() {
        dao.deleteAllManifests()
    }
}
