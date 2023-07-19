package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.common.entities.composite.ArtworkUser
import com.listocalixto.android.rembrandt.core.domain.repository.UserRepo
import com.listocalixto.android.rembrandt.core.domain.utility.ArtworkQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ObserveAllFavoriteArtworksUseCase @Inject constructor(
    private val userRepo: UserRepo,
    private val observeAllArtworksUser: ObserveAllArtworksUserUseCase,
) {

    operator fun invoke(): Flow<List<ArtworkUser>> {
        return userRepo.user.map { it.favoriteArtworks }
            .distinctUntilChanged()
            .flatMapLatest { favoritesArtworks ->
                when {
                    favoritesArtworks.isEmpty() -> flowOf(emptyList())
                    else -> observeAllArtworksUser(query = ArtworkQuery(filterFavoriteIds = favoritesArtworks))
                }
            }
    }
}
