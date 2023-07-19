package com.listocalixto.android.rembrandt.core.local.artwork.dto

import kotlinx.serialization.Serializable

@Serializable
data class MetadataLocal(
    val label: String,
    val value: String
)
