package com.listocalixto.android.rembrandt.core.network.artwork

import com.listocalixto.android.rembrandt.core.network.artwork.response.GetArtworkByIdResponse
import com.listocalixto.android.rembrandt.core.network.artwork.response.GetArtworksResponse

interface ArtworkService {
    suspend fun getArtworksByPage(page: Int): GetArtworksResponse
    suspend fun getArtworkById(id: Long): GetArtworkByIdResponse
}
