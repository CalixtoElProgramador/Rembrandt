package com.listocalixto.android.rembrandt.data.source.remote.implementation

import com.listocalixto.android.rembrandt.data.mapper.remote.ArtworkRemoteToEntity
import com.listocalixto.android.rembrandt.data.source.remote.configuration.HttpRoutes
import com.listocalixto.android.rembrandt.data.source.remote.implementation.response.main.GetArtworksResponse
import com.listocalixto.android.rembrandt.domain.entity.Artwork
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteArtworkDataSourceImpl @Inject constructor(
    private val mapper: ArtworkRemoteToEntity,
    private val client: HttpClient,
) : RemoteArtworkDataSource {

    override suspend fun getArtworksByPage(page: String): List<Artwork> {
        val response: GetArtworksResponse = client.get(HttpRoutes.ARTWORKS) {
            url {
                parameters.append(JUST_PUBLIC_DOMAIN_NAME, JUST_PUBLIC_DOMAIN_VALUE)
                parameters.append(PAGE_NAME, page)
                parameters.append(LIMIT_NAME, LIMIT_VALUE)
                parameters.append(LIMIT_NAME, LIMIT_VALUE)
                parameters.append(FIELDS_NAME, FIELDS_VALUE)
            }
        }
        return response.data.map { mapper.map(it) }
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

    companion object {
        private const val JUST_PUBLIC_DOMAIN_NAME = "query[term][is_public_domain]"
        private const val JUST_PUBLIC_DOMAIN_VALUE = "true"
        private const val PAGE_NAME = "page"
        private const val LIMIT_NAME = "limit"
        private const val LIMIT_VALUE = "100"
        private const val FIELDS_NAME = "fields"
        private const val FIELDS_VALUE =
            "id,title,thumbnail,has_not_been_viewed_much,date_start,date_end,date_display,artist_display,place_of_origin,dimensions,medium_display,credit_line,color,latitude,longitude,gallery_title,gallery_id,artwork_type_title,artwork_type_id,artist_id,artist_title,category_ids,category_titles,term_titles,image_id"
    }
}
