package com.listocalixto.android.rembrandt.data.artwork.remote

import com.listocalixto.android.rembrandt.common.entities.Artwork

internal interface RemoteArtworkDataSource {
    suspend fun getArtworksByPage(page: Int): List<Artwork>
    suspend fun getArtworkById(id: Long): Artwork
}
