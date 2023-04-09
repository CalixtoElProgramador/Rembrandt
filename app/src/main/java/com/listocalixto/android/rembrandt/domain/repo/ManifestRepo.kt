package com.listocalixto.android.rembrandt.domain.repo

import com.listocalixto.android.rembrandt.domain.entity.Manifest

interface ManifestRepo {
    suspend fun getManifestByArtworkId(id: Long, forceUpdate: Boolean): Manifest
}
