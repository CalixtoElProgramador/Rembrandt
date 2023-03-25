package com.listocalixto.android.rembrandt.data.source.local.configuration

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.listocalixto.android.rembrandt.data.source.local.implementation.table.main.ArtworkLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtworkDao {

    @Query("SELECT * FROM artwork")
    fun getArtworks(): Flow<ArtworkLocal>

    @Query("SELECT * FROM artwork WHERE id = :artworkId")
    fun getArtworkById(artworkId: Long): Flow<ArtworkLocal?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtwork(artwork: ArtworkLocal)
}
