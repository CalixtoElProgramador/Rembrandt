package com.listocalixto.android.rembrandt.domain.model

data class Manifest(
    val artworkId: Long,
    val id: String,
    val description: String,
    val metadata: List<Metadata>
)
