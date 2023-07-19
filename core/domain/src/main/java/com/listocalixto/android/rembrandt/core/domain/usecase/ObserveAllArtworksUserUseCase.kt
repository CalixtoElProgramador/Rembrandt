package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.common.entities.composite.ArtworkUser
import com.listocalixto.android.rembrandt.core.domain.repository.ArtworkRepo
import com.listocalixto.android.rembrandt.core.domain.repository.UserRepo
import com.listocalixto.android.rembrandt.core.domain.utility.ArtworkQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class ObserveAllArtworksUserUseCase @Inject constructor(
    private val artworkRepo: ArtworkRepo,
    private val userRepo: UserRepo,
) {
    operator fun invoke(query: ArtworkQuery): Flow<List<ArtworkUser>> {
        return artworkRepo.observeArtworksByPage(page = 1, query = query)
            .combine(userRepo.user) { artworks, user ->
                artworks.map { artwork -> ArtworkUser(artwork, user) }
            }
    }
}
