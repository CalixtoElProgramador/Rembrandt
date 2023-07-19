package com.listocalixto.android.rembrandt.core.network.artwork.response

import com.listocalixto.android.rembrandt.core.network.artwork.dto.RemoteArtwork
import com.listocalixto.android.rembrandt.core.network.artwork.dto.RemoteConfig
import com.listocalixto.android.rembrandt.core.network.artwork.dto.RemoteInfo
import com.listocalixto.android.rembrandt.core.network.artwork.dto.PaginationRemote
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetArtworksResponse(
    @SerialName("config")
    val config: RemoteConfig,
    @SerialName("data")
    val data: List<RemoteArtwork>,
    @SerialName("info")
    val info: RemoteInfo,
    @SerialName("pagination")
    val pagination: PaginationRemote,
)
