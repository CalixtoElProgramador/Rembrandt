package com.listocalixto.android.rembrandt.data.source.remote.implementation.response.main

import com.listocalixto.android.rembrandt.data.source.remote.implementation.model.DescriptionRemote
import com.listocalixto.android.rembrandt.data.source.remote.implementation.model.MetadataRemote
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetManifestResponse(
    @SerialName("attribution")
    val attribution: String,
    @SerialName("description")
    val description: List<DescriptionRemote>,
    @SerialName("@id")
    val id: String,
    @SerialName("logo")
    val logo: String,
    @SerialName("metadata")
    val metadata: List<MetadataRemote>
)
