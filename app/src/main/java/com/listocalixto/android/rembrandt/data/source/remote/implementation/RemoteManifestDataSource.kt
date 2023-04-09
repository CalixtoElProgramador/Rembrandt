package com.listocalixto.android.rembrandt.data.source.remote.implementation

import com.listocalixto.android.rembrandt.domain.entity.Manifest

interface RemoteManifestDataSource {

    suspend fun fetchManifestByArtworkId(id: Long): Manifest
}
