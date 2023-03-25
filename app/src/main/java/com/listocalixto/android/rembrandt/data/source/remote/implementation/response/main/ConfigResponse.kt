package com.listocalixto.android.rembrandt.data.source.remote.implementation.response.main


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigResponse(
    @SerialName("iiif_url")
    val iiifUrl: String?,
    @SerialName("website_url")
    val websiteUrl: String?
)