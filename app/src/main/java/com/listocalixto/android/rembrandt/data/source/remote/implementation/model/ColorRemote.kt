package com.listocalixto.android.rembrandt.data.source.remote.implementation.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ColorRemote(
    @SerialName("h")
    val h: Int?,
    @SerialName("l")
    val l: Int?,
    @SerialName("percentage")
    val percentage: Double?,
    @SerialName("population")
    val population: Int?,
    @SerialName("s")
    val s: Int?
)
