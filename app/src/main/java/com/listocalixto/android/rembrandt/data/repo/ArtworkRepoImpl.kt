package com.listocalixto.android.rembrandt.data.repo

import com.listocalixto.android.rembrandt.data.source.remote.implementation.RemoteArtworkDataSource
import com.listocalixto.android.rembrandt.domain.entity.Artwork
import com.listocalixto.android.rembrandt.domain.repo.ArtworkRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ArtworkRepoImpl @Inject constructor(
    private val remoteDataSource: RemoteArtworkDataSource,
) : ArtworkRepo {

    override fun getArtworksByPage(page: String): Flow<Result<Set<Artwork>>> = flow {
        try {
            emit(Result.success(remoteDataSource.getArtworksByPage(page).first()))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)

    override fun getArtworkById(id: Long): Flow<Artwork> = flow {
    }

    override fun getArtworksByConcept(concept: String): Flow<Set<Artwork>> {
        TODO("Not yet implemented")
    }

    override fun getArtworksByArtistId(id: Long): Flow<Set<Artwork>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllArtworks() {
    }
}
