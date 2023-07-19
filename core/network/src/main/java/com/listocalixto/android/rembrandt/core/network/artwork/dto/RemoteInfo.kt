package com.listocalixto.android.rembrandt.core.network.artwork.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteInfo(
    @SerialName("license_links")
    val licenseLinks: List<String?>?,
    @SerialName("license_text")
    val licenseText: String?,
    @SerialName("version")
    val version: String?
)
