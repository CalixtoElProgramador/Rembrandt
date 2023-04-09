package com.listocalixto.android.rembrandt.data.source.remote.implementation.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SequenceRemote(
    @SerialName("canvases")
    val canvases: List<CanvaseRemote>,
    @SerialName("@type")
    val type: String
)
