package com.listocalixto.android.rembrandt.core.ui.states

import com.listocalixto.android.rembrandt.common.entities.Collection

data class CollectionUiState internal constructor(
    val id: String,
    val name: String,
    val description: String,
    val isPrivate: Boolean,
    val principalImageUrl: String?,
    val artworkIds: Set<Long>,
    val artworkAlreadyBelongsToTheCollection: Boolean,
    val isEditModeActivated: Boolean = false,
) {
    constructor(collection: Collection, artworkId: Long) : this(
        id = collection.id,
        name = collection.name,
        description = collection.description,
        isPrivate = collection.isPrivate,
        principalImageUrl = collection.principalImageUrl,
        artworkIds = collection.artworkIds,
        artworkAlreadyBelongsToTheCollection = collection.artworkIds.contains(artworkId),
    )

    fun toEntity() = Collection(
        id = id,
        name = name,
        description = description,
        isPrivate = isPrivate,
        principalImageUrl = principalImageUrl,
        artworkIds = artworkIds,
    )

    fun List<CollectionUiState>.toEntities(): List<Collection> = map { toEntity() }
}
