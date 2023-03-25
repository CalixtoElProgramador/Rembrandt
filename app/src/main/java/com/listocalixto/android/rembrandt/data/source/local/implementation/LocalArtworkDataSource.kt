package com.listocalixto.android.rembrandt.data.source.local.implementation

import com.listocalixto.android.rembrandt.domain.entity.Artwork
import kotlinx.coroutines.flow.Flow

interface LocalArtworkDataSource {

    fun getArtworks(): Flow<Set<Artwork>>
    fun getArtworkById(id: Long): Flow<Artwork>
    fun getArtworksByConcept(concept: String): Flow<List<Artwork>>
    fun getArtworksByArtistId(id: Long): Flow<List<Artwork>>
    suspend fun insertArtwork(artwork: Artwork)
    suspend fun deleteAllArtworks()
    suspend fun saveFavorite(artworkId: Long)
    suspend fun deleteFavorite(artworkId: Long)
}
