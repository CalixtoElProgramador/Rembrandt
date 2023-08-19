package com.listocalixto.android.rembrandt.core.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.listocalixto.android.rembrandt.core.ui.states.ArtworkCharacteristicUiState
import com.listocalixto.android.rembrandt.core.ui.viewholder.ArtworkCharacteristicViewHolder

class ArtworkCharacteristicAdapter :
    ListAdapter<ArtworkCharacteristicUiState, ArtworkCharacteristicViewHolder>(
        ArtworkCharacteristicDiffUtil,
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ArtworkCharacteristicViewHolder {
        return ArtworkCharacteristicViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ArtworkCharacteristicViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(label = item.label, value = item.value)
    }

    object ArtworkCharacteristicDiffUtil : DiffUtil.ItemCallback<ArtworkCharacteristicUiState>() {
        override fun areItemsTheSame(
            oldItem: ArtworkCharacteristicUiState,
            newItem: ArtworkCharacteristicUiState,
        ): Boolean {
            return oldItem.label == newItem.label
        }

        override fun areContentsTheSame(
            oldItem: ArtworkCharacteristicUiState,
            newItem: ArtworkCharacteristicUiState,
        ): Boolean {
            return oldItem == newItem
        }
    }
}
