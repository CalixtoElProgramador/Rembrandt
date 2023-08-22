package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.common.entities.Collection
import com.listocalixto.android.rembrandt.core.domain.repository.CollectionRepo
import javax.inject.Inject

class ToggleArtworkIdToCollectionUseCase @Inject constructor(
    private val repo: CollectionRepo,
) {
    suspend operator fun invoke(artworkId: Long, collectionId: String) {
        repo.getCollectionById(collectionId)?.apply {
            val newArtworkIds = longs(artworkId)
            repo.upsertCollection(copy(artworkIds = newArtworkIds.toSet()))
        }
    }

    suspend operator fun invoke(artworkId: Long, collection: Collection) {
        with(collection) {
            val newArtworkIds = longs(artworkId)
            repo.upsertCollection(copy(artworkIds = newArtworkIds.toSet()))
        }
    }

    private fun Collection.longs(artworkId: Long): Set<Long> {
        val newArtworkIds = artworkIds.toMutableSet().also { artworksSaved ->
            if (artworksSaved.contains(artworkId)) {
                artworksSaved.remove(artworkId)
            } else {
                artworksSaved.add(artworkId)
            }
        }
        return newArtworkIds
    }
}
