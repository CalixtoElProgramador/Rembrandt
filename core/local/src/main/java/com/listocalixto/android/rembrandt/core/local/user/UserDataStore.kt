package com.listocalixto.android.rembrandt.core.local.user

import com.listocalixto.android.rembrandt.core.local.user.dto.LocalUser
import kotlinx.coroutines.flow.Flow

interface UserDataStore {
    val preferences: Flow<LocalUser>
    suspend fun toggleFavoriteArtworkId(id: Long, isFavorite: Boolean)
}
