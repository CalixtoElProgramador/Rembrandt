package com.listocalixto.android.rembrandt.data.source.remote.implementation.response.main

import com.listocalixto.android.rembrandt.data.source.remote.implementation.model.ArtworkRemote
import com.listocalixto.android.rembrandt.data.source.remote.implementation.model.ConfigRemote
import com.listocalixto.android.rembrandt.data.source.remote.implementation.model.InfoRemote
import com.listocalixto.android.rembrandt.data.source.remote.implementation.model.PaginationRemote
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetArtworksResponse(
    @SerialName("config")
    val config: ConfigRemote,
    @SerialName("data")
    val data: List<ArtworkRemote>,
    @SerialName("info")
    val info: InfoRemote,
    @SerialName("pagination")
    val pagination: PaginationRemote,
)
