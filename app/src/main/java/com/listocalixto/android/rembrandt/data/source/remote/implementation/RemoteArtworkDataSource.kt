package com.listocalixto.android.rembrandt.data.source.remote.implementation

import com.listocalixto.android.rembrandt.domain.entity.Artwork
import kotlinx.coroutines.flow.Flow

interface RemoteArtworkDataSource {
    suspend fun getArtworksByPage(page: String): Flow<Set<Artwork>>
    fun getArtworkById(id: Long): Flow<Artwork>
    fun getArtworksByConcept(concept: String): Flow<List<Artwork>>
    fun getArtworksByArtistId(id: Long): Flow<List<Artwork>>
}
