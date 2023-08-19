package com.listocalixto.android.rembrandt.core.ui.states

import com.listocalixto.android.rembrandt.common.entities.composite.ArtworkUser
import kotlin.random.Random

data class ArtworkCarouselUiState internal constructor(
    override val id: Long,
    val artworks: List<ArtworkUserUiState>,
) : HomeItem {
    constructor(artworkUsers: List<ArtworkUser>) : this(
        id = Random(511).nextLong(),
        artworks = artworkUsers.map { ArtworkUserUiState(it) },
    )
}
