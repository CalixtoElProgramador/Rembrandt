package com.listocalixto.android.rembrandt.core.ui.states

import com.listocalixto.android.rembrandt.common.entities.composite.ArtworkUser
import kotlin.random.Random

data class ArtworkCollageUiState internal constructor(
    override val id: Long,
    val topArtwork: ArtworkUserUiState,
    val centerStartArtwork: ArtworkUserUiState,
    val centerEndArtwork: ArtworkUserUiState,
    val bottomStartArtwork: ArtworkUserUiState,
    val bottomEndArtwork: ArtworkUserUiState,
    val title: String,
    val subtitle: String?,
) : HomeItem {
    constructor(artworkUsers: List<ArtworkUser>, title: String, subtitle: String? = null) : this(
        id = Random(312).nextLong(),
        topArtwork = ArtworkUserUiState(artworkUsers[0]),
        centerStartArtwork = ArtworkUserUiState(artworkUsers[1]),
        centerEndArtwork = ArtworkUserUiState(artworkUsers[2]),
        bottomStartArtwork = ArtworkUserUiState(artworkUsers[3]),
        bottomEndArtwork = ArtworkUserUiState(artworkUsers[4]),
        title = title,
        subtitle = subtitle,
    )
}
