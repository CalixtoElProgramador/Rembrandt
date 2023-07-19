package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.core.domain.repository.UserRepo
import javax.inject.Inject

class ToggleFavoriteArtworkIdUseCase @Inject constructor(
    private val userRepo: UserRepo,
) {
    suspend operator fun invoke(id: Long, isFavorite: Boolean) {
        userRepo.toggleFavoriteArtworkId(id, !isFavorite)
    }
}
