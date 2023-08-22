package com.listocalixto.android.rembrandt.core.local.entities.manifest

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ManifestDao {

    @Query("SELECT * FROM manifests WHERE id = :id")
    suspend fun getManifestById(id: String): List<LocalManifest>

    @Upsert
    suspend fun insertManifest(item: LocalManifest)

    @Query("DELETE FROM manifests")
    suspend fun deleteAllManifests()
}
