package com.listocalixto.android.rembrandt.data.collection.mapper

import com.listocalixto.android.rembrandt.common.dependencies.Mapper
import com.listocalixto.android.rembrandt.common.entities.Collection
import com.listocalixto.android.rembrandt.core.local.entities.collection.LocalCollection
import javax.inject.Inject

internal class LocalCollectionToEntity @Inject constructor() :
    Mapper<LocalCollection, Collection>() {

    override fun map(input: LocalCollection) = Collection(
        id = input.id,
        name = input.name,
        description = input.description,
        isPrivate = input.isPrivate,
        principalImageUrl = input.principalImageUrl,
        artworkIds = input.artworkIds,
    )

    override fun reverseMap(input: Collection) = LocalCollection(
        id = input.id,
        name = input.name,
        description = input.description,
        isPrivate = input.isPrivate,
        principalImageUrl = input.principalImageUrl,
        artworkIds = input.artworkIds,
    )
}
