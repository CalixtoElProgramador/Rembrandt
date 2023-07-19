package com.listocalixto.android.rembrandt.feature.home

import com.listocalixto.android.rembrandt.core.ui.states.ArtworkUserUiState

internal data class HomeUiState(
    val isLoading: Boolean = false,
    val artworks: List<ArtworkUserUiState> = emptyList(),
)
