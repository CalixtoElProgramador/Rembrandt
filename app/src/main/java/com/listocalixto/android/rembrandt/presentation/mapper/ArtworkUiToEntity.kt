package com.listocalixto.android.rembrandt.presentation.mapper

import com.listocalixto.android.rembrandt.core.Mapper
import com.listocalixto.android.rembrandt.domain.entity.Artwork
import com.listocalixto.android.rembrandt.presentation.entity.ArtworkUi

class ArtworkUiToEntity : Mapper<ArtworkUi, Artwork> {

    override fun map(value: ArtworkUi) = Artwork(
        id = value.id
    )

    override fun reverseMap(value: Artwork) = ArtworkUi(
        id = value.id
    )

    override fun map(values: List<ArtworkUi>) = values.map { map(it) }

    override fun reverseMap(values: List<Artwork>) = values.map { reverseMap(it) }
}
