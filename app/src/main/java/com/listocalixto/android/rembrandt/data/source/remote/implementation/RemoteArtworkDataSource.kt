package com.listocalixto.android.rembrandt.data.source.remote.implementation

import com.listocalixto.android.rembrandt.domain.entity.Artwork
import kotlinx.coroutines.flow.Flow

interface RemoteArtworkDataSource {
    suspend fun getArtworksByPage(page: String = FIRST_PAGE): List<Artwork>
    fun getArtworkById(id: Long): Flow<Artwork>
    fun getArtworksByConcept(concept: String): Flow<List<Artwork>>
    fun getArtworksByArtistId(id: Long): Flow<List<Artwork>>

    companion object {
        private const val FIRST_PAGE = "1"
    }
}
