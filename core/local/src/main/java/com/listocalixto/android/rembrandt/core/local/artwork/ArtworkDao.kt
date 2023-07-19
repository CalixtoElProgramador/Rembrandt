package com.listocalixto.android.rembrandt.core.local.artwork

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.listocalixto.android.rembrandt.core.local.artwork.table.LocalArtwork
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtworkDao {

    @Query(
        """
        SELECT * FROM artworks
        WHERE
            CASE WHEN :userFilterFavoriteArtworkIds
                THEN id IN (:filterFavoriteArtworkIds)
                ELSE 1
            END
        ORDER BY title DESC
        """,
    )
    fun observeAllArtworks(
        userFilterFavoriteArtworkIds: Boolean = false,
        filterFavoriteArtworkIds: Set<Long> = emptySet(),
    ): Flow<List<LocalArtwork>>

    @Query("SELECT * FROM artworks WHERE id = :artworkId")
    fun observeArtworkById(artworkId: Long): Flow<List<LocalArtwork>>

    @Upsert
    suspend fun insertArtwork(artwork: LocalArtwork)

    @Query("DELETE FROM artworks")
    suspend fun deleteAllArtworks()
}
