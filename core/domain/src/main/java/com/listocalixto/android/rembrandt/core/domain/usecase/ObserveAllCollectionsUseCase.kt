package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.common.entities.Collection
import com.listocalixto.android.rembrandt.core.domain.repository.CollectionRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveAllCollectionsUseCase @Inject constructor(
    private val repo: CollectionRepo,
) {
    operator fun invoke(): Flow<List<Collection>> {
        return repo.observeAllCollections()
    }
}
