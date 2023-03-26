package com.listocalixto.android.rembrandt.data.repo

import com.listocalixto.android.rembrandt.core.networkBoundResource
import com.listocalixto.android.rembrandt.data.source.local.implementation.LocalArtworkDataSource
import com.listocalixto.android.rembrandt.data.source.remote.implementation.RemoteArtworkDataSource
import com.listocalixto.android.rembrandt.domain.entity.Artwork
import com.listocalixto.android.rembrandt.domain.repo.ArtworkRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArtworkRepoImpl @Inject constructor(
    private val localDataSource: LocalArtworkDataSource,
    private val remoteDataSource: RemoteArtworkDataSource,
) : ArtworkRepo {

    override fun observeArtworksByPage(page: String): Flow<Result<Set<Artwork>>> =
        networkBoundResource(
            query = {
                localDataSource.observeAllArtworks()
            },
            fetch = {
                remoteDataSource.getArtworksByPage(page)
            },
            saveFetchResult = { artworks ->
                localDataSource.deleteAllArtworks()
                artworks.forEach { localDataSource.insertArtwork(it) }
            },
            shouldFetch = {
                localDataSource.getAllArtworks().isEmpty()
            },
        ).flowOn(Dispatchers.IO)

    override fun observeArtworkById(id: Long): Flow<Result<Artwork>> = flow {
        try {
            val artwork = localDataSource.observeArtworkById(id).first()
            emit(Result.success(artwork))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)

    override fun observeArtworksByQuery(concept: String): Flow<Set<Artwork>> {
        TODO("Not yet implemented")
    }

    override fun getArtworksByArtistId(id: Long): Flow<Set<Artwork>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllArtworks() = withContext(Dispatchers.IO) {
    }

    override suspend fun updateArtwork(artwork: Artwork) = withContext<Unit>(Dispatchers.IO) {
        launch { localDataSource.updateArtwork(artwork) }
    }

    override suspend fun getArtworkById(id: Long): Artwork {
        return localDataSource.getArtworkById(id)
    }

    override fun getArtworksByQuery(concept: String): List<Artwork> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllArtworks(): List<Artwork> =
        withContext(Dispatchers.IO) {
            return@withContext localDataSource.getAllArtworks().ifEmpty {
                val remoteArtworks = remoteDataSource.getArtworksByPage()
                remoteArtworks.forEach { localDataSource.insertArtwork(it) }
                localDataSource.getAllArtworks()
            }
        }
}
