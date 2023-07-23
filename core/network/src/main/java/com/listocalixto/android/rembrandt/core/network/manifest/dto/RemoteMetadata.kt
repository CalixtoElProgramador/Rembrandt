package com.listocalixto.android.rembrandt.core.network.manifest.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteMetadata(
    @SerialName("label")
    val label: String,
    @SerialName("value")
    val value: String
)