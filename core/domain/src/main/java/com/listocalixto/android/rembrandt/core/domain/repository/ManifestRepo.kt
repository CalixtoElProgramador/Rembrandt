package com.listocalixto.android.rembrandt.core.domain.repository

import com.listocalixto.android.rembrandt.common.entities.Manifest

interface ManifestRepo {
    suspend fun getManifestByArtworkId(id: Long): Manifest
    suspend fun deleteAllManifests()
}
