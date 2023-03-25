package com.listocalixto.android.rembrandt.data.mapper.local

import com.listocalixto.android.rembrandt.core.Mapper
import com.listocalixto.android.rembrandt.data.source.local.implementation.table.main.ArtworkLocal
import com.listocalixto.android.rembrandt.domain.entity.Artwork

class ArtworkLocalToEntity : Mapper<ArtworkLocal, Artwork> {

    override fun map(value: ArtworkLocal) = Artwork(
        id = value.id
    )

    override fun map(values: List<ArtworkLocal>): List<Artwork> = values.map { map(it) }

    override fun reverseMap(value: Artwork) = ArtworkLocal(
        id = value.id
    )

    override fun reverseMap(values: List<Artwork>) = values.map { reverseMap(it) }
}
