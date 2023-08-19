package com.listocalixto.android.rembrandt.core.network.artwork

import com.listocalixto.android.rembrandt.core.network.HttpRoutes.ARTWORK
import com.listocalixto.android.rembrandt.core.network.HttpRoutes.ARTWORKS
import com.listocalixto.android.rembrandt.core.network.artwork.response.GetArtworkByIdResponse
import com.listocalixto.android.rembrandt.core.network.artwork.response.GetArtworksResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path
import javax.inject.Inject

internal class ArtworkServiceImpl @Inject constructor(
    private val client: HttpClient,
) : ArtworkService {

    override suspend fun getArtworksByPage(page: Int): GetArtworksResponse {
        val response: GetArtworksResponse = client.get(ARTWORKS) {
            url {
                parameters.append(JUST_PUBLIC_DOMAIN_NAME, JUST_PUBLIC_DOMAIN_VALUE)
                parameters.append(PAGE_NAME, page.toString())
                parameters.append(LIMIT_NAME, LIMIT_VALUE)
                parameters.append(FIELDS_NAME, FIELDS_VALUE)
            }
        }.body()
        return response
    }

    override suspend fun getArtworkById(id: Long): GetArtworkByIdResponse {
        val response: GetArtworkByIdResponse = client.get(ARTWORK) {
            url {
                path(id.toString())
                parameters.append(FIELDS_NAME, FIELDS_VALUE)
            }
        }.body()
        return response
    }

    companion object {
        private const val JUST_PUBLIC_DOMAIN_NAME = "query[term][is_public_domain]"
        private const val JUST_PUBLIC_DOMAIN_VALUE = "true"
        private const val PAGE_NAME = "page"
        private const val LIMIT_NAME = "limit"
        private const val LIMIT_VALUE = "100"
        private const val FIELDS_NAME = "fields"
        private const val FIELDS_VALUE =
            "id,title,thumbnail,has_not_been_viewed_much,date_start,date_end,date_display,artist_display,place_of_origin,dimensions,medium_display,credit_line,color,latitude,longitude,gallery_title,gallery_id,artwork_type_title,artwork_type_id,artist_id,artist_title,category_ids,category_titles,term_titles,image_id,inscriptions,style_id,style_title"
    }
}
