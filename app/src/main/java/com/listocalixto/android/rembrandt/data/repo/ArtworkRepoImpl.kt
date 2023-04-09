package com.listocalixto.android.rembrandt.data.repo

import com.listocalixto.android.rembrandt.core.networkBoundResource
import com.listocalixto.android.rembrandt.data.source.local.implementation.LocalArtworkDataSource
import com.listocalixto.android.rembrandt.data.source.remote.implementation.RemoteArtworkDataSource
import com.listocalixto.android.rembrandt.domain.entity.Artwork
import com.listocalixto.android.rembrandt.domain.model.Manifest
import com.listocalixto.android.rembrandt.domain.repo.ArtworkRepo
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtworkRepoImpl @Inject constructor(
    private val localDataSource: LocalArtworkDataSource,
    private val remoteDataSource: RemoteArtworkDataSource
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
            shouldFetch = { artworks ->
                artworks.isEmpty()
            }
        ).flowOn(Dispatchers.IO)

    override fun observeArtworkById(id: Long): Flow<Result<Artwork>> = flow {
        try {
            localDataSource.observeArtworkById(id).collect {
                emit(Result.success(it))
            }
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

    override suspend fun fetchManifestByArtworkId(id: Long): Manifest =
        withContext(Dispatchers.IO) {
            return@withContext remoteDataSource.fetchManifestByArtworkId(id)
        }
}
