package com.listocalixto.android.rembrandt.data.source.remote.implementation.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetadataRemote(
    @SerialName("label")
    val label: String,
    @SerialName("value")
    val value: String
)
