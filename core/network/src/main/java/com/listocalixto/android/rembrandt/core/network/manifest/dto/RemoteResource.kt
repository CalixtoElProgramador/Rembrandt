package com.listocalixto.android.rembrandt.core.network.manifest.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteResource(
    @SerialName("height")
    val height: Int = -1,
    @SerialName("@id")
    val id: String,
    @SerialName("service")
    val service: RemoteService,
    @SerialName("@type")
    val type: String,
    @SerialName("width")
    val width: Int = -1
)