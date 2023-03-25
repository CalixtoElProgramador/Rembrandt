package com.listocalixto.android.rembrandt.data.source.remote.implementation.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InfoRemote(
    @SerialName("license_links")
    val licenseLinks: List<String?>?,
    @SerialName("license_text")
    val licenseText: String?,
    @SerialName("version")
    val version: String?
)