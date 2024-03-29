package com.listocalixto.android.rembrandt.core.network.artwork.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteColor(
    @SerialName("h")
    val h: Int,
    @SerialName("l")
    val l: Int,
    @SerialName("percentage")
    val percentage: Double,
    @SerialName("population")
    val population: Int,
    @SerialName("s")
    val s: Int
)
