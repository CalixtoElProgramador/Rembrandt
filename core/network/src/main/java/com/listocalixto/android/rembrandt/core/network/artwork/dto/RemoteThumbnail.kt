package com.listocalixto.android.rembrandt.core.network.artwork.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteThumbnail(
    @SerialName("alt_text")
    val altText: String,
    @SerialName("height")
    val height: Int?,
    @SerialName("lqip")
    val lqip: String,
    @SerialName("width")
    val width: Int?
)
