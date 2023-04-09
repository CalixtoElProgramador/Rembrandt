package com.listocalixto.android.rembrandt.data.source.remote.implementation.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageRemote(
    @SerialName("motivation")
    val motivation: String,
    @SerialName("on")
    val on: String,
    @SerialName("resource")
    val resource: ResourceRemote,
    @SerialName("@type")
    val type: String
)
