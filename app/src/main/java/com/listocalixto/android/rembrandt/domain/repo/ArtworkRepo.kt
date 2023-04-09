package com.listocalixto.android.rembrandt.domain.repo

import com.listocalixto.android.rembrandt.domain.entity.Artwork
import com.listocalixto.android.rembrandt.domain.model.Manifest
import kotlinx.coroutines.flow.Flow

interface ArtworkRepo {

    fun observeArtworksByPage(page: String): Flow<Result<Set<Artwork>>>

    fun observeArtworkById(id: Long): Flow<Result<Artwork>>

    fun observeArtworksByQuery(concept: String): Flow<Set<Artwork>>
    fun getArtworksByQuery(concept: String): List<Artwork>

    fun getArtworksByArtistId(id: Long): Flow<Set<Artwork>>

    suspend fun deleteAllArtworks()

    suspend fun updateArtwork(artwork: Artwork)

    suspend fun getArtworkById(id: Long): Artwork

    suspend fun getAllArtworks(): List<Artwork>

    suspend fun fetchManifestByArtworkId(id: Long): Manifest
}
