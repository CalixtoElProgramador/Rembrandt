package com.listocalixto.android.rembrandt.data.artwork

import com.listocalixto.android.rembrandt.common.dependencies.di.Dispatcher
import com.listocalixto.android.rembrandt.common.dependencies.di.RDispatchers.IO
import com.listocalixto.android.rembrandt.common.entities.Artwork
import com.listocalixto.android.rembrandt.core.domain.repository.ArtworkRepo
import com.listocalixto.android.rembrandt.core.domain.utility.ArtworkQuery
import com.listocalixto.android.rembrandt.data.artwork.local.LocalArtworkDataSource
import com.listocalixto.android.rembrandt.data.artwork.remote.RemoteArtworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class ArtworkRepoImpl @Inject constructor(
    private val remoteSource: RemoteArtworkDataSource,
    private val localSource: LocalArtworkDataSource,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) : ArtworkRepo {
    override fun observeArtworksByPage(page: Int, query: ArtworkQuery): Flow<List<Artwork>> {
        return localSource.observeAllArtworks(query).map { artworks ->
            artworks.ifEmpty {
                withContext(ioDispatcher) {
                    remoteSource.getArtworksByPage(page).also { newArtworks ->
                        localSource.deleteAllArtworks()
                        localSource.insertArtworks(newArtworks)
                    }
                }
            }
        }
    }

    override suspend fun getAllArtworks(): Set<Artwork> {
        return withContext(ioDispatcher) {
            localSource.getAllArtworks()
        }
    }

    override fun observeArtworkById(id: Long): Flow<Artwork> {
        return localSource.observeArtworkById(id).map { currentArtwork ->
            currentArtwork ?: withContext(ioDispatcher) {
                remoteSource.getArtworkById(id).also { localSource.insertArtwork(it) }
            }
        }
    }

    override suspend fun getArtworkById(id: Long): Artwork? {
        return withContext(ioDispatcher) {
            localSource.getArtworkById(id)
        }
    }

    override suspend fun deleteAllArtworks() {
        withContext(ioDispatcher) {
            localSource.deleteAllArtworks()
        }
    }
}
