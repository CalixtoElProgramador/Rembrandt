package com.listocalixto.android.rembrandt.data.source.remote.implementation.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResourceRemote(
    @SerialName("height")
    val height: Int,
    @SerialName("@id")
    val id: String,
    @SerialName("service")
    val serviceRemote: ServiceRemote,
    @SerialName("@type")
    val type: String,
    @SerialName("width")
    val width: Int
)
