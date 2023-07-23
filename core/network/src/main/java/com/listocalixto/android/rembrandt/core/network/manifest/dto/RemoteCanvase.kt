package com.listocalixto.android.rembrandt.core.network.manifest.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteCanvase(
    @SerialName("height")
    val height: Int,
    @SerialName("@id")
    val id: String,
    @SerialName("images")
    val images: List<RemoteImage>,
    @SerialName("label")
    val label: String,
    @SerialName("@type")
    val type: String,
    @SerialName("width")
    val width: Int
)