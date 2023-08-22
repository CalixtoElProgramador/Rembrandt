package com.listocalixto.android.feature.savetocollection.saveto

import com.listocalixto.android.rembrandt.core.ui.states.CollectionUiState

internal data class SaveToCollectionUiState(
    val artworkId: Long = -1,
    val collections: List<CollectionUiState> = emptyList(),
    val isEditModeActivated: Boolean = false,
) {
    val shouldDisplayEmptyState = collections.isEmpty()
}
