package com.listocalixto.android.rembrandt.domain.result

import com.listocalixto.android.rembrandt.domain.entity.Artwork
import com.listocalixto.android.rembrandt.domain.utility.RecommendationType

data class GetRecommendedArtworksByArtworkResult(
    val artworksRecommended: List<Artwork>,
    val recommendationTypes: List<RecommendationType>,
)
