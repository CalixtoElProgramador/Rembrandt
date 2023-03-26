package com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork

import com.listocalixto.android.rembrandt.domain.entity.Artwork
import com.listocalixto.android.rembrandt.domain.utility.RecommendationType

data class ArtworkDetailData(
    val artwork: Artwork? = null,
    val artworksRecommended: List<Artwork> = emptyList(),
    val recommendationTypes: List<RecommendationType> = emptyList(),
)
