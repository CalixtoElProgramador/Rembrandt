package com.listocalixto.android.rembrandt.core.network.manifest.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteRendering(
    @SerialName("format")
    val format: String,
    @SerialName("@id")
    val id: String,
    @SerialName("label")
    val label: String
)