package com.listocalixto.android.rembrandt.core.network.manifest.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteSequence(
    @SerialName("canvases")
    val canvases: List<RemoteCanvase>,
    @SerialName("@type")
    val type: String
)