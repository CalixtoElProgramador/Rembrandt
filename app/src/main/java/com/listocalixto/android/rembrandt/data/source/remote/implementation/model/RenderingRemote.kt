package com.listocalixto.android.rembrandt.data.source.remote.implementation.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RenderingRemote(
    @SerialName("format")
    val format: String,
    @SerialName("@id")
    val id: String,
    @SerialName("label")
    val label: String
)
