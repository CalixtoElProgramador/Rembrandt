package com.listocalixto.android.rembrandt.common.entities

import com.listocalixto.android.rembrandt.common.entities.model.Metadata

data class Manifest(
    val artworkId: Long,
    val id: String,
    val description: String,
    val metadata: List<Metadata>
)
