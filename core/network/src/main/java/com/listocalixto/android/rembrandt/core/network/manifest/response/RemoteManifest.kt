package com.listocalixto.android.rembrandt.core.network.manifest.response

import com.listocalixto.android.rembrandt.core.network.manifest.dto.RemoteDescription
import com.listocalixto.android.rembrandt.core.network.manifest.dto.RemoteMetadata
import com.listocalixto.android.rembrandt.core.network.manifest.dto.RemoteRendering
import com.listocalixto.android.rembrandt.core.network.manifest.dto.RemoteSequence
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteManifest(
    @SerialName("attribution")
    val attribution: String,
    @SerialName("@context")
    val context: String,
    @SerialName("description")
    val description: List<RemoteDescription>,
    @SerialName("@id")
    val id: String,
    @SerialName("label")
    val label: String,
    @SerialName("logo")
    val logo: String,
    @SerialName("metadata")
    val metadata: List<RemoteMetadata>,
    @SerialName("rendering")
    val rendering: RemoteRendering,
    @SerialName("sequences")
    val sequences: List<RemoteSequence>,
    @SerialName("@type")
    val type: String,
    @SerialName("within")
    val within: String,
)
