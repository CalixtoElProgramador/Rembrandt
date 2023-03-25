package com.listocalixto.android.rembrandt.data.source.local.implementation

import com.listocalixto.android.rembrandt.data.mapper.local.ArtworkLocalToEntity
import com.listocalixto.android.rembrandt.data.source.local.configuration.ArtworkDao
import com.listocalixto.android.rembrandt.domain.entity.Artwork
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalArtworkDataSourceImpl @Inject constructor(
    private val dao: ArtworkDao,
    private val mapper: ArtworkLocalToEntity,
) : LocalArtworkDataSource {

    override fun getArtworks(): Flow<Set<Artwork>> {
        return dao.getArtworks().map { artworks ->
            mapper.map(artworks.toList()).toSet()
        }
    }

    override fun getArtworkById(id: Long): Flow<Artwork> = flow {
    }

    override fun getArtworksByConcept(concept: String): Flow<List<Artwork>> {
        TODO("Not yet implemented")
    }

    override fun getArtworksByArtistId(id: Long): Flow<List<Artwork>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertArtwork(artwork: Artwork) {
        dao.insertArtwork(mapper.reverseMap(artwork))
    }

    override suspend fun deleteAllArtworks() {
        dao.deleteAllArtworks()
    }

    override suspend fun saveFavorite(artworkId: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavorite(artworkId: Long) {
        TODO("Not yet implemented")
    }
}
