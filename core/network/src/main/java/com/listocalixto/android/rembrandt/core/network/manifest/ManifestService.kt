package com.listocalixto.android.rembrandt.core.network.manifest

import com.listocalixto.android.rembrandt.core.network.manifest.response.RemoteManifest

interface ManifestService {
    suspend fun getManifestByArtworkId(id: Long): RemoteManifest
}
