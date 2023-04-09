package com.listocalixto.android.rembrandt.data.source.remote.implementation.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CanvaseRemote(
    @SerialName("height")
    val height: Int?,
    @SerialName("@id")
    val id: String,
    @SerialName("images")
    val images: List<ImageRemote>,
    @SerialName("label")
    val label: String,
    @SerialName("@type")
    val type: String,
    @SerialName("width")
    val width: Int?
)
