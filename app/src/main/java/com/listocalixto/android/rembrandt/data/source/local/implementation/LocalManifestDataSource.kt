package com.listocalixto.android.rembrandt.data.source.local.implementation

import com.listocalixto.android.rembrandt.domain.entity.Manifest

interface LocalManifestDataSource {

    suspend fun getManifestByArtworkId(artworkId: Long): Manifest?
    suspend fun insertManifest(manifest: Manifest)
}
