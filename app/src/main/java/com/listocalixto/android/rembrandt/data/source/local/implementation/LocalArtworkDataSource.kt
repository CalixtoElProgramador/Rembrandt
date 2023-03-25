package com.listocalixto.android.rembrandt.data.source.local.implementation

import com.listocalixto.android.rembrandt.domain.entity.Artwork
import kotlinx.coroutines.flow.Flow

interface LocalArtworkDataSource {

    fun observeArtworks(): Flow<Set<Artwork>>
    fun observeArtworkById(id: Long): Flow<Artwork>
    fun getArtworksByConcept(concept: String): Flow<List<Artwork>>
    fun getArtworksByArtistId(id: Long): Flow<List<Artwork>>
    suspend fun insertArtwork(artwork: Artwork)
    suspend fun deleteAllArtworks()
    suspend fun saveFavorite(artworkId: Long)
    suspend fun deleteFavorite(artworkId: Long)
    suspend fun updateArtwork(artwork: Artwork)
    suspend fun getArtworkById(id: Long): Artwork
    suspend fun getArtworks(): Set<Artwork>
}
