package com.listocalixto.android.rembrandt.data.source.local.configuration

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.listocalixto.android.rembrandt.data.source.local.implementation.table.main.ManifestTable

@Dao
interface ManifestDao {

    @Query("SELECT * FROM manifests WHERE artworkId = :artworkId")
    suspend fun getManifestByArtworkId(artworkId: Long): ManifestTable?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertManifest(manifest: ManifestTable)
}
