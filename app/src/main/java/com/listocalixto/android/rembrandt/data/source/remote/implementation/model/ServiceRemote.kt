package com.listocalixto.android.rembrandt.data.source.remote.implementation.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ServiceRemote(
    @SerialName("@context")
    val context: String,
    @SerialName("@id")
    val id: String,
    @SerialName("profile")
    val profile: String
)
