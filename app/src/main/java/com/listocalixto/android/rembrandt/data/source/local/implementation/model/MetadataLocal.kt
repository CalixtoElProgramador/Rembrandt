package com.listocalixto.android.rembrandt.data.source.local.implementation.model

import kotlinx.serialization.Serializable

@Serializable
data class MetadataLocal(
    val label: String,
    val value: String
)
