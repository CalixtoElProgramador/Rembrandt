package com.listocalixto.android.rembrandt.data.source.local.configuration

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.listocalixto.android.rembrandt.data.source.local.implementation.table.main.ArtworkTable
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtworkDao {

    @Query("SELECT * FROM artworks")
    fun observeArtworks(): Flow<List<ArtworkTable>>

    @Query("SELECT * FROM artworks WHERE id = :artworkId")
    fun observeArtworkById(artworkId: Long): Flow<ArtworkTable?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtwork(artwork: ArtworkTable)

    @Query("DELETE FROM artworks")
    suspend fun deleteAllArtworks()

    @Update
    suspend fun updateArtwork(artwork: ArtworkTable)

    @Query("SELECT * FROM artworks WHERE id = :id")
    suspend fun getArtworkById(id: Long): ArtworkTable

    @Query("SELECT * FROM artworks")
    fun getArtworks(): List<ArtworkTable>
}
