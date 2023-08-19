package com.listocalixto.android.rembrandt.feature.home

import com.listocalixto.android.rembrandt.core.ui.states.HomeItem

internal data class HomeUiState(
    val isLoading: Boolean = false,
    val items: List<HomeItem> = emptyList(),
)
