package com.listocalixto.android.rembrandt.core.local.user

import android.util.Log
import androidx.datastore.core.DataStore
import com.listocalixto.android.rembrandt.core.local.UserPreferences
import com.listocalixto.android.rembrandt.core.local.copy
import com.listocalixto.android.rembrandt.core.local.user.dto.LocalUser
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

internal class UserDataStoreImpl @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>,
) : UserDataStore {

    override val preferences = userPreferences.data.map {
        LocalUser(
            favoriteArtworksId = it.favoriteArtworksIdMap.keys,
        )
    }

    override suspend fun toggleFavoriteArtworkId(id: Long, isFavorite: Boolean) {
        try {
            userPreferences.updateData {
                it.copy {
                    if (isFavorite) {
                        favoriteArtworksId.put(id, true)
                    } else {
                        favoriteArtworksId.remove(id)
                    }
                }
            }
        } catch (ioException: IOException) {
            Log.e("UserDataStore", "Failed to update user preferences", ioException)
        }
    }
}
