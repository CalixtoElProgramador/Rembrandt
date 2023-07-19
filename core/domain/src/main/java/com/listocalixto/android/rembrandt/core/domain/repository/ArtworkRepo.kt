package com.listocalixto.android.rembrandt.core.domain.repository

import com.listocalixto.android.rembrandt.common.entities.Artwork
import com.listocalixto.android.rembrandt.core.domain.utility.ArtworkQuery
import kotlinx.coroutines.flow.Flow

interface ArtworkRepo {
    fun observeArtworksByPage(
        page: Int,
        query: ArtworkQuery = ArtworkQuery(
            filterFavoriteIds = null,
        ),
    ): Flow<List<Artwork>>

    fun observeArtworkById(id: Long): Flow<Artwork>
    suspend fun deleteAllArtworks()
}
