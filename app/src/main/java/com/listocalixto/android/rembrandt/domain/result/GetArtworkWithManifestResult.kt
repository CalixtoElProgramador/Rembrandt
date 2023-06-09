package com.listocalixto.android.rembrandt.domain.result

import com.listocalixto.android.rembrandt.domain.entity.Artwork
import com.listocalixto.android.rembrandt.domain.model.Manifest

data class GetArtworkWithManifestResult(
    val artwork: Artwork,
    val manifest: Manifest?
)
