package com.listocalixto.android.rembrandt.data.source.remote.implementation

import com.listocalixto.android.rembrandt.data.mapper.remote.ArtworkResponseToEntity
import com.listocalixto.android.rembrandt.data.source.remote.configuration.ArtworkService
import com.listocalixto.android.rembrandt.domain.entity.Artwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class RemoteArtworkDataSourceImpl(
    private val service: ArtworkService,
    private val mapper: ArtworkResponseToEntity
) : RemoteArtworkDataSource {

    override suspend fun getArtworks(): List<Artwork> = withContext(Dispatchers.IO) {
        val response = service.fetchArtworksByPage(1).first()
        mapper.map(response.data)
    }

    override fun getArtworkById(id: Long): Flow<Artwork> {
        TODO("Not yet implemented")
    }

    override fun getArtworksByConcept(concept: String): Flow<List<Artwork>> {
        TODO("Not yet implemented")
    }

    override fun getArtworksByArtistId(id: Long): Flow<List<Artwork>> {
        TODO("Not yet implemented")
    }
}
