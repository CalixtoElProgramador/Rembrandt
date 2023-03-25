package com.listocalixto.android.rembrandt.data.source.remote.configuration

import com.listocalixto.android.rembrandt.data.source.remote.implementation.response.main.GetArtworksResponse
import kotlinx.coroutines.flow.Flow

interface ArtworkService {

    fun fetchArtworksByPage(page: Int): Flow<GetArtworksResponse>
}
