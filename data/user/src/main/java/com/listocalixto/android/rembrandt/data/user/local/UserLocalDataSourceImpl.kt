package com.listocalixto.android.rembrandt.data.user.local

import com.listocalixto.android.rembrandt.common.dependencies.di.Dispatcher
import com.listocalixto.android.rembrandt.common.dependencies.di.RDispatchers.Default
import com.listocalixto.android.rembrandt.common.entities.User
import com.listocalixto.android.rembrandt.core.local.entities.user.UserDataStore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class UserLocalDataSourceImpl @Inject constructor(
    private val datsStore: UserDataStore,
    @Dispatcher(Default) private val defaultDispatcher: CoroutineDispatcher,
) : UserLocalDataSource {
    override val user: Flow<User> = datsStore.preferences.map { localUser ->
        User(
            favoriteArtworkIds = localUser.favoriteArtworkIds,
        )
    }.flowOn(defaultDispatcher)

    override suspend fun toggleFavoriteArtworkId(id: Long, isFavorite: Boolean) {
        datsStore.toggleFavoriteArtworkId(id, isFavorite)
    }
}
