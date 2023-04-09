package com.listocalixto.android.rembrandt.domain.entity

import com.listocalixto.android.rembrandt.domain.model.Metadata

data class Manifest(
    val artworkId: Long,
    val id: String,
    val description: String,
    val metadata: List<Metadata>
)
