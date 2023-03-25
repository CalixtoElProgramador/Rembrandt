package com.listocalixto.android.rembrandt.domain.result

import com.listocalixto.android.rembrandt.domain.entity.Artwork

data class GetArtworksByPageResult(
    val artworks: Set<Artwork>,
)
