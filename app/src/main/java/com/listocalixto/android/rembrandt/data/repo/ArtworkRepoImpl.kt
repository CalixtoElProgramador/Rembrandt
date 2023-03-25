package com.listocalixto.android.rembrandt.data.repo

import com.listocalixto.android.rembrandt.core.networkBoundResource
import com.listocalixto.android.rembrandt.data.source.local.implementation.LocalArtworkDataSource
import com.listocalixto.android.rembrandt.data.source.remote.implementation.RemoteArtworkDataSource
import com.listocalixto.android.rembrandt.domain.entity.Artwork
import com.listocalixto.android.rembrandt.domain.repo.ArtworkRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

class ArtworkRepoImpl(
    private val localDataSource: LocalArtworkDataSource,
    private val remoteDataSource: RemoteArtworkDataSource
) : ArtworkRepo {

    override fun getAllArtworks(): Flow<Result<Set<Artwork>>> = networkBoundResource(
        query = {
            localDataSource.getArtworks()
        },
        fetch = {
            remoteDataSource.getArtworks()
        },
        saveFetchResult = { artworks ->
            localDataSource.deleteAllArtworks()
            artworks.forEach { localDataSource.insertArtwork(it) }
        }
    )

    override fun getArtworkById(id: Long): Flow<Artwork> = flow {
        localDataSource.getArtworkById(id).firstOrNull()?.let {
            emit(it)
        } ?: run {
            val artwork = remoteDataSource.getArtworkById(id).first()
            localDataSource.insertArtwork(artwork)
            getArtworkById(id)
        }
    }

    override fun getArtworksByConcept(concept: String): Flow<Set<Artwork>> {
        TODO("Not yet implemented")
    }

    override fun getArtworksByArtistId(id: Long): Flow<Set<Artwork>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllArtworks() {
        localDataSource.deleteAllArtworks()
    }
}
