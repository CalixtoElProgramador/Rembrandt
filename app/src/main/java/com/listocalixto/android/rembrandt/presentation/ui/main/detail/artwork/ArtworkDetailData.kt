package com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork

import com.listocalixto.android.rembrandt.domain.entity.Artwork
import com.listocalixto.android.rembrandt.domain.entity.Manifest
import com.listocalixto.android.rembrandt.domain.utility.RecommendationType

data class ArtworkDetailData(
    val artwork: Artwork? = null,
    val manifest: Manifest? = null,
    val artworksRecommended: List<Artwork>? = null,
    val recommendationTypes: List<RecommendationType>? = null
)
