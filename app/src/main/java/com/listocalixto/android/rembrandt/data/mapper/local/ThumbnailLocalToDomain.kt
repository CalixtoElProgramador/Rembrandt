package com.listocalixto.android.rembrandt.data.mapper.local

import com.listocalixto.android.rembrandt.core.Mapper
import com.listocalixto.android.rembrandt.data.source.local.implementation.model.ThumbnailLocal
import com.listocalixto.android.rembrandt.domain.model.Thumbnail

class ThumbnailLocalToDomain : Mapper<ThumbnailLocal, Thumbnail> {

    override fun map(value: ThumbnailLocal) = Thumbnail(
        altText = value.altText,
        height = value.height,
        lqip = value.lqip,
        width = value.width,
    )

    override fun map(values: List<ThumbnailLocal>): List<Thumbnail> {
        return values.map { map(it) }
    }

    override fun reverseMap(value: Thumbnail) = ThumbnailLocal(
        altText = value.altText,
        height = value.height,
        lqip = value.lqip,
        width = value.width,
    )

    override fun reverseMap(values: List<Thumbnail>): List<ThumbnailLocal> {
        return values.map { reverseMap(it) }
    }
}
