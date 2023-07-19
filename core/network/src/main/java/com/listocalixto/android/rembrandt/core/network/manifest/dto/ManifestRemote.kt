package com.listocalixto.android.rembrandt.core.network.manifest.dto

data class ManifestRemote(
    val artworkId: Long,
    val id: String,
    val description: String,
    val metadata: List<MetadataRemote>
)
