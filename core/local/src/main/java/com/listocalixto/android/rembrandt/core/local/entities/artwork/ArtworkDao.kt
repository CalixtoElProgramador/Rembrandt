package com.listocalixto.android.rembrandt.core.local.entities.artwork

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.listocalixto.android.rembrandt.core.local.entities.artwork.table.LocalArtwork
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

    @Query(
        """
            SELECT * FROM artworks
            WHERE
                CASE WHEN :userFilterByArtistId
                    THEN artistId in (:filterByArtistId)
                    ELSE 1
                END
             OR
                CASE WHEN :userFilterByArtworkTypeId
                    THEN artworkTypeId in (:filterByArtworkTypeId)
                    ELSE 1
                END
             OR
                CASE WHEN :userFilterByGalleryId
                    THEN galleryId in (:filterByGalleryId)
                    ELSE 1
                END
            ORDER BY title ASC
        """,
    )
    suspend fun getAllArtworks(
        userFilterByArtistId: Boolean = false,
        filterByArtistId: Set<Long> = emptySet(),
        userFilterByArtworkTypeId: Boolean = false,
        filterByArtworkTypeId: Set<Long> = emptySet(),
        userFilterByGalleryId: Boolean = false,
        filterByGalleryId: Set<Long> = emptySet(),
    ): List<LocalArtwork>

    @Query("SELECT * FROM artworks WHERE id = :artworkId")
    fun observeArtworkById(artworkId: Long): Flow<List<LocalArtwork>>

    @Query("SELECT * FROM artworks WHERE id = :artworkId")
    suspend fun getArtworkById(artworkId: Long): List<LocalArtwork>

    @Upsert
    suspend fun insertArtwork(artwork: LocalArtwork)

    @Query("DELETE FROM artworks")
    suspend fun deleteAllArtworks()
}
