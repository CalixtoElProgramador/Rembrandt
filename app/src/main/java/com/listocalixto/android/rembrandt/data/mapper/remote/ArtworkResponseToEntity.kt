package com.listocalixto.android.rembrandt.data.mapper.remote

import com.listocalixto.android.rembrandt.core.Mapper
import com.listocalixto.android.rembrandt.data.source.remote.implementation.response.main.ArtworkResponse
import com.listocalixto.android.rembrandt.domain.entity.Artwork

class ArtworkResponseToEntity : Mapper<ArtworkResponse, Artwork> {

    override fun map(value: ArtworkResponse) = Artwork(
        id = value.id
    )

    override fun map(values: List<ArtworkResponse>) = values.map { map(it) }

    override fun reverseMap(value: Artwork): ArtworkResponse {
        TODO("Not yet implemented")
    }

    override fun reverseMap(values: List<Artwork>): List<ArtworkResponse> {
        TODO("Not yet implemented")
    }
}
