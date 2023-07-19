package com.listocalixto.android.rembrandt.data.artwork.mapper

import com.listocalixto.android.rembrandt.common.dependencies.Mapper
import com.listocalixto.android.rembrandt.common.entities.model.Thumbnail
import com.listocalixto.android.rembrandt.core.network.artwork.dto.RemoteThumbnail
import javax.inject.Inject

internal class RemoteThumbnailToModel @Inject constructor() : Mapper<RemoteThumbnail, Thumbnail>() {
    override fun map(input: RemoteThumbnail) = Thumbnail(
        altText = input.altText,
        height = input.height,
        lqip = input.lqip,
        width = input.width,
    )

    override fun reverseMap(input: Thumbnail) = RemoteThumbnail(
        altText = input.altText,
        height = input.height,
        lqip = input.lqip,
        width = input.width,
    )
}
