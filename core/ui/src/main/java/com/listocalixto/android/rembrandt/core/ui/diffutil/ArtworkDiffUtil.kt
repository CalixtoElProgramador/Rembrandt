package com.listocalixto.android.rembrandt.core.ui.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.listocalixto.android.rembrandt.core.ui.states.ArtworkUserUiState

object ArtworkDiffUtil : DiffUtil.ItemCallback<ArtworkUserUiState>() {
    override fun areItemsTheSame(
        oldItem: ArtworkUserUiState,
        newItem: ArtworkUserUiState,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ArtworkUserUiState,
        newItem: ArtworkUserUiState,
    ): Boolean {
        return oldItem == newItem
    }
}
