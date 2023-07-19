package com.listocalixto.android.rembrandt.core.network.manifest.response

import com.listocalixto.android.rembrandt.core.network.manifest.dto.DescriptionRemote
import com.listocalixto.android.rembrandt.core.network.manifest.dto.MetadataRemote
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
