package com.listocalixto.android.rembrandt.data.manifest.remote

import com.listocalixto.android.rembrandt.common.entities.Manifest

interface RemoteManifestDataSource {
    suspend fun getManifestByArtworkId(id: Long): Manifest
}
