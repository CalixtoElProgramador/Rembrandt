package com.listocalixto.android.rembrandt.data.mapper.remote

import com.listocalixto.android.rembrandt.core.Mapper
import com.listocalixto.android.rembrandt.data.source.remote.implementation.model.ThumbnailRemote
import com.listocalixto.android.rembrandt.domain.model.Thumbnail
import javax.inject.Inject

class ThumbnailRemoteToDomain @Inject constructor() : Mapper<ThumbnailRemote, Thumbnail> {

    override fun map(value: ThumbnailRemote) = Thumbnail(
        altText = value.altText ?: "",
        height = value.height ?: 0,
        lqip = value.lqip ?: "",
        width = value.width ?: 0
    )

    override fun map(values: List<ThumbnailRemote>): List<Thumbnail> {
        return values.map { map(it) }
    }

    override fun reverseMap(value: Thumbnail) = ThumbnailRemote(
        altText = value.altText,
        height = value.height,
        lqip = value.lqip,
        width = value.width
    )

    override fun reverseMap(values: List<Thumbnail>): List<ThumbnailRemote> {
        return values.map { reverseMap(it) }
    }
}
