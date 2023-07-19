package com.listocalixto.android.rembrandt.core.network.artwork.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteConfig(
    @SerialName("iiif_url")
    val iiifUrl: String?,
    @SerialName("website_url")
    val websiteUrl: String?
)
