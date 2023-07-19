package com.listocalixto.android.rembrandt.data.user.local

import com.listocalixto.android.rembrandt.common.entities.User
import kotlinx.coroutines.flow.Flow

internal interface UserLocalDataSource {
    val user: Flow<User>
    suspend fun toggleFavoriteArtworkId(id: Long, isFavorite: Boolean)
}
