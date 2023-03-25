package com.listocalixto.android.rembrandt.data.source.local.configuration

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.listocalixto.android.rembrandt.data.source.local.implementation.table.main.ArtworkTable
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtworkDao {

    @Query("SELECT * FROM artwork")
    fun getArtworks(): Flow<ArtworkTable>

    @Query("SELECT * FROM artwork WHERE id = :artworkId")
    fun getArtworkById(artworkId: Long): Flow<ArtworkTable?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtwork(artwork: ArtworkTable)
}
