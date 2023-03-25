package com.listocalixto.android.rembrandt.data.source.remote.implementation.response.main


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InfoResponse(
    @SerialName("license_links")
    val licenseLinks: List<String?>?,
    @SerialName("license_text")
    val licenseText: String?,
    @SerialName("version")
    val version: String?
)