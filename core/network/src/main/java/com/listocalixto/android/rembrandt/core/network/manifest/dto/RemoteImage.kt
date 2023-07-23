package com.listocalixto.android.rembrandt.core.network.manifest.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteImage(
    @SerialName("motivation")
    val motivation: String,
    @SerialName("on")
    val on: String,
    @SerialName("resource")
    val resource: RemoteResource,
    @SerialName("@type")
    val type: String
)