package com.listocalixto.android.rembrandt.data.manifest.local

import com.listocalixto.android.rembrandt.common.entities.Manifest

interface LocalManifestDataSource {
    suspend fun getManifestById(id: String): Manifest?
    suspend fun insertManifest(manifest: Manifest)
    suspend fun deleteAllManifests()
}
