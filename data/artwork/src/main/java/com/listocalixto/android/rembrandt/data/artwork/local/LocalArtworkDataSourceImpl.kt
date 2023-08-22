package com.listocalixto.android.rembrandt.data.artwork.local

import com.listocalixto.android.rembrandt.common.dependencies.di.Dispatcher
import com.listocalixto.android.rembrandt.common.dependencies.di.RDispatchers.Default
import com.listocalixto.android.rembrandt.common.entities.Artwork
import com.listocalixto.android.rembrandt.core.domain.utility.ArtworkQuery
import com.listocalixto.android.rembrandt.core.local.entities.artwork.ArtworkDao
import com.listocalixto.android.rembrandt.data.artwork.mapper.LocalArtworkToEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class LocalArtworkDataSourceImpl @Inject constructor(
    private val dao: ArtworkDao,
    private val mapper: LocalArtworkToEntity,
    @Dispatcher(Default) private val defaultDispatcher: CoroutineDispatcher,
) : LocalArtworkDataSource {
    override fun observeAllArtworks(query: ArtworkQuery): Flow<List<Artwork>> {
        val localArtworks = dao.observeAllArtworks(
            userFilterFavoriteArtworkIds = query.filterFavoriteIds != null,
            filterFavoriteArtworkIds = query.filterFavoriteIds ?: emptySet(),
        )
        return localArtworks.map { mapper.map(it) }.flowOn(defaultDispatcher)
    }

    override fun observeArtworkById(id: Long): Flow<Artwork?> {
        val localArtwork = dao.observeArtworkById(id)
        return localArtwork.map {
            if (it.isNotEmpty()) {
                mapper.map(it.first())
            } else {
                null
            }
        }.flowOn(defaultDispatcher)
    }

    override suspend fun getAllArtworks(): Set<Artwork> {
        val localArtworks = dao.getAllArtworks()
        return withContext(defaultDispatcher) {
            localArtworks.map {
                mapper.map(it)
            }.toSet()
        }
    }

    override suspend fun getArtworkById(id: Long): Artwork? {
        val localArtwork = dao.getArtworkById(id)
        return localArtwork.firstOrNull()?.let {
            withContext(defaultDispatcher) { mapper.map(it) }
        }
    }

    override suspend fun insertArtwork(artwork: Artwork) {
        val localArtwork = withContext(defaultDispatcher) {
            mapper.reverseMap(artwork)
        }
        dao.insertArtwork(localArtwork)
    }

    override suspend fun insertArtworks(artworks: List<Artwork>) {
        val localArtworks = withContext(defaultDispatcher) {
            mapper.reverseMap(artworks)
        }
        localArtworks.forEach { dao.insertArtwork(it) }
    }

    override suspend fun deleteAllArtworks() {
        dao.deleteAllArtworks()
    }
}
