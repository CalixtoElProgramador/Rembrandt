package com.listocalixto.android.rembrandt.data.source.local.configuration

import androidx.room.Dao

@Dao
interface ManifestDao {

   /* @Query("SELECT * FROM manifests WHERE artworkId = :artworkId")
    suspend fun getManifestByArtworkId(artworkId: Long): ManifestTable?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertManifest(manifest: ManifestTable)*/
}
