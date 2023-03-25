package com.listocalixto.android.rembrandt.data.source.remote.implementation.response.main

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ThumbnailResponse(
    @SerialName("alt_text")
    val altText: String?,
    @SerialName("height")
    val height: Int?,
    @SerialName("lqip")
    val lqip: String?,
    @SerialName("width")
    val width: Int?
)
