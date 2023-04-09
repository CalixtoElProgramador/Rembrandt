package com.listocalixto.android.rembrandt.data.source.remote.implementation.response.main

import com.listocalixto.android.rembrandt.data.source.remote.implementation.model.DescriptionRemote
import com.listocalixto.android.rembrandt.data.source.remote.implementation.model.MetadataRemote
import com.listocalixto.android.rembrandt.data.source.remote.implementation.model.RenderingRemote
import com.listocalixto.android.rembrandt.data.source.remote.implementation.model.SequenceRemote
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetManifestResponse(
    @SerialName("attribution")
    val attribution: String,
    @SerialName("@context")
    val context: String,
    @SerialName("description")
    val description: List<DescriptionRemote>,
    @SerialName("@id")
    val id: String,
    @SerialName("label")
    val label: String,
    @SerialName("logo")
    val logo: String,
    @SerialName("metadata")
    val metadata: List<MetadataRemote>,
    @SerialName("rendering")
    val rendering: RenderingRemote,
    @SerialName("sequences")
    val sequences: List<SequenceRemote>,
    @SerialName("@type")
    val type: String,
    @SerialName("within")
    val within: String
)
