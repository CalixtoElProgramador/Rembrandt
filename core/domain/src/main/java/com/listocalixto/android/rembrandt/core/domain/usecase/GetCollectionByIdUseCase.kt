package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.common.entities.Collection
import com.listocalixto.android.rembrandt.core.domain.repository.CollectionRepo
import javax.inject.Inject

class GetCollectionByIdUseCase @Inject constructor(
    private val repo: CollectionRepo,
) {
    suspend operator fun invoke(id: String): Collection? {
        return repo.getCollectionById(id)
    }
}
