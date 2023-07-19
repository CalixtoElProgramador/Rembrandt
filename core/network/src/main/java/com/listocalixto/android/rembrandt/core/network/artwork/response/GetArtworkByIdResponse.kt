package com.listocalixto.android.rembrandt.core.network.artwork.response

import com.listocalixto.android.rembrandt.core.network.artwork.dto.RemoteArtwork
import com.listocalixto.android.rembrandt.core.network.artwork.dto.RemoteConfig
import com.listocalixto.android.rembrandt.core.network.artwork.dto.RemoteInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetArtworkByIdResponse(
    @SerialName("config")
    val config: RemoteConfig,
    @SerialName("data")
    val data: RemoteArtwork,
    @SerialName("info")
    val info: RemoteInfo,
)
