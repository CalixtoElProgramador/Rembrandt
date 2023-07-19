package com.listocalixto.android.rembrandt.core.domain.repository

import com.listocalixto.android.rembrandt.common.entities.User
import kotlinx.coroutines.flow.Flow

interface UserRepo {
    val user: Flow<User>
    suspend fun toggleFavoriteArtworkId(id: Long, isFavorite: Boolean)
}
