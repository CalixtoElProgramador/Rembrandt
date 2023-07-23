package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.common.entities.composite.ArtworkUser
import com.listocalixto.android.rembrandt.core.domain.repository.ArtworkRepo
import com.listocalixto.android.rembrandt.core.domain.repository.UserRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class ObserveArtworkUserByIdUseCase @Inject constructor(
    private val artworkRepo: ArtworkRepo,
    private val userRepo: UserRepo,
) {

    operator fun invoke(artworkId: Long): Flow<ArtworkUser> {
        return artworkRepo.observeArtworkById(artworkId).combine(userRepo.user) { artwork, user ->
            ArtworkUser(artwork, user)
        }
    }
}
