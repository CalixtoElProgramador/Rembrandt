package com.listocalixto.android.rembrandt.feature.favorites

import com.listocalixto.android.rembrandt.core.ui.states.ArtworkUserUiState

internal data class FavoritesUiState(
    val artworks: List<ArtworkUserUiState> = emptyList(),
)
