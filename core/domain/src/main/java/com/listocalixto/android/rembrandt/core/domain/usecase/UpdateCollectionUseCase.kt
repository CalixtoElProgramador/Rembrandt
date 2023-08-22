package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.common.entities.Collection
import com.listocalixto.android.rembrandt.core.domain.repository.CollectionRepo
import javax.inject.Inject

class UpdateCollectionUseCase @Inject constructor(
    private val repo: CollectionRepo,
) {
    suspend operator fun invoke(collection: Collection) {
        repo.upsertCollection(collection)
    }
}
