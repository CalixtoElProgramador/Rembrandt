package com.listocalixto.android.rembrandt.data.artwork.local

import com.listocalixto.android.rembrandt.common.entities.Artwork
import com.listocalixto.android.rembrandt.core.domain.utility.ArtworkQuery
import kotlinx.coroutines.flow.Flow

internal interface LocalArtworkDataSource {
    fun observeAllArtworks(query: ArtworkQuery): Flow<List<Artwork>>
    fun observeArtworkById(id: Long): Flow<Artwork?>
    suspend fun getAllArtworks(): Set<Artwork>
    suspend fun getArtworkById(id: Long): Artwork?
    suspend fun insertArtwork(artwork: Artwork)
    suspend fun insertArtworks(artworks: List<Artwork>)
    suspend fun deleteAllArtworks()
}
