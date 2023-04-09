package com.listocalixto.android.rembrandt.data.source.local.implementation

import com.listocalixto.android.rembrandt.data.mapper.local.ManifestLocalToModel
import com.listocalixto.android.rembrandt.data.source.local.configuration.ManifestDao
import javax.inject.Inject

class LocalManifestDataSourceImpl @Inject constructor(
    private val dao: ManifestDao,
    private val mapper: ManifestLocalToModel
) : LocalManifestDataSource {

    /*override suspend fun getManifestByArtworkId(artworkId: Long): Manifest? {
        val manifest = dao.getManifestByArtworkId(artworkId)
        return manifest?.let { mapper.map(it) }
    }

    override suspend fun insertManifest(manifest: Manifest) {
        dao.insertManifest(mapper.reverseMap(manifest))
    }*/
}
