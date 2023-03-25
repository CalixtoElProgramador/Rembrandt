package com.listocalixto.android.rembrandt.domain.repo

import com.listocalixto.android.rembrandt.domain.entity.Artwork
import kotlinx.coroutines.flow.Flow

interface ArtworkRepo {

    fun getAllArtworks(): Flow<Result<Set<Artwork>>>

    fun getArtworkById(id: Long): Flow<Artwork>

    fun getArtworksByConcept(concept: String): Flow<Set<Artwork>>

    fun getArtworksByArtistId(id: Long): Flow<Set<Artwork>>

    suspend fun deleteAllArtworks()
}
