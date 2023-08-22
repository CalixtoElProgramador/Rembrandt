package com.listocalixto.android.rembrandt.data.artwork.mapper

import com.listocalixto.android.rembrandt.common.dependencies.Mapper
import com.listocalixto.android.rembrandt.common.entities.model.Thumbnail
import com.listocalixto.android.rembrandt.core.local.entities.artwork.dto.LocalThumbnail
import javax.inject.Inject

internal class LocalThumbnailToModel @Inject constructor() : Mapper<LocalThumbnail, Thumbnail>() {
    override fun map(input: LocalThumbnail) = Thumbnail(
        altText = input.altText,
        height = input.height,
        lqip = input.lqip,
        width = input.width,
    )

    override fun reverseMap(input: Thumbnail) = LocalThumbnail(
        altText = input.altText,
        height = input.height,
        lqip = input.lqip,
        width = input.width,
    )
}
