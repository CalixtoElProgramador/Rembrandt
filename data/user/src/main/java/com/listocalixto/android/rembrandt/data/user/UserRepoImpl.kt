package com.listocalixto.android.rembrandt.data.user

import com.listocalixto.android.rembrandt.common.dependencies.di.Dispatcher
import com.listocalixto.android.rembrandt.common.dependencies.di.RDispatchers.IO
import com.listocalixto.android.rembrandt.common.entities.User
import com.listocalixto.android.rembrandt.core.domain.repository.UserRepo
import com.listocalixto.android.rembrandt.data.user.local.UserLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class UserRepoImpl @Inject constructor(
    private val localSource: UserLocalDataSource,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) : UserRepo {
    override val user: Flow<User> = localSource.user

    override suspend fun toggleFavoriteArtworkId(id: Long, isFavorite: Boolean) {
        localSource.toggleFavoriteArtworkId(id, isFavorite)
    }
}
