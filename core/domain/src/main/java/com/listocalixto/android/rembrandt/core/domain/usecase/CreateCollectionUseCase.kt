package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.common.entities.Collection
import com.listocalixto.android.rembrandt.core.domain.repository.CollectionRepo
import javax.inject.Inject

class CreateCollectionUseCase @Inject constructor(
    private val repo: CollectionRepo,
    private val createUserCollectionId: CreateCollectionIdUseCase,
) {

    suspend operator fun invoke(name: String, description: String, isPrivate: Boolean): String {
        val id: String = createUserCollectionId()
        val collection = Collection(
            id = id,
            name = name,
            description = description,
            isPrivate = isPrivate,
            principalImageUrl = null,
            artworkIds = emptySet(),
        )
        repo.upsertCollection(collection)
        return id
    }
}
