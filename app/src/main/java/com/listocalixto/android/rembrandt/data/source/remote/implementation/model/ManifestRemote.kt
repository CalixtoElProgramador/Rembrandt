package com.listocalixto.android.rembrandt.data.source.remote.implementation.model

data class ManifestRemote(
    val artworkId: Long,
    val id: String,
    val description: String,
    val metadata: List<MetadataRemote>
)
