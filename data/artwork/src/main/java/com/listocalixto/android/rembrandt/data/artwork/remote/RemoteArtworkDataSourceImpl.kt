package com.listocalixto.android.rembrandt.data.artwork.remote

import com.listocalixto.android.rembrandt.common.dependencies.di.Dispatcher
import com.listocalixto.android.rembrandt.common.dependencies.di.RDispatchers.Default
import com.listocalixto.android.rembrandt.common.entities.Artwork
import com.listocalixto.android.rembrandt.core.network.artwork.ArtworkService
import com.listocalixto.android.rembrandt.data.artwork.mapper.RemoteArtworkToEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RemoteArtworkDataSourceImpl @Inject constructor(
    private val service: ArtworkService,
    private val remoteArtworkToEntity: RemoteArtworkToEntity,
    @Dispatcher(Default) private val defaultDispatcher: CoroutineDispatcher,
) : RemoteArtworkDataSource {
    override suspend fun getArtworksByPage(page: Int): List<Artwork> {
        val response = service.getArtworksByPage(page)
        val remoteArtworks = response.data
        return withContext(defaultDispatcher) { remoteArtworkToEntity.map(remoteArtworks) }
    }

    override suspend fun getArtworkById(id: Long): Artwork {
        val response = service.getArtworkById(id)
        val remoteArtwork = response.data
        return withContext(defaultDispatcher) { remoteArtworkToEntity.map(remoteArtwork) }
    }
}
