package com.listocalixto.android.rembrandt.data.source.remote.implementation.response.main

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetArtworksResponse(
    @SerialName("config")
    val config: ConfigResponse,
    @SerialName("data")
    val data: List<ArtworkResponse>,
    @SerialName("info")
    val info: InfoResponse,
    @SerialName("pagination")
    val pagination: PaginationResponse
)
