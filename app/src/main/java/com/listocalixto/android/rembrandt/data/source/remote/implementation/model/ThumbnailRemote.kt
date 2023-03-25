package com.listocalixto.android.rembrandt.data.source.remote.implementation.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ThumbnailRemote(
    @SerialName("alt_text")
    val altText: String?,
    @SerialName("height")
    val height: Int?,
    @SerialName("lqip")
    val lqip: String?,
    @SerialName("width")
    val width: Int?
)
