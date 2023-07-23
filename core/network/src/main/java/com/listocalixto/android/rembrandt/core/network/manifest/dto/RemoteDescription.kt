package com.listocalixto.android.rembrandt.core.network.manifest.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteDescription(
    @SerialName("language")
    val language: String,
    @SerialName("value")
    val value: String
)