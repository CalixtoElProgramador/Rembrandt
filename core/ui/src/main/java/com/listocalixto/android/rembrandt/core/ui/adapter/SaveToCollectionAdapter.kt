package com.listocalixto.android.rembrandt.core.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.listocalixto.android.rembrandt.core.ui.states.CollectionUiState
import com.listocalixto.android.rembrandt.core.ui.viewholder.SaveToCollectionViewHolder

typealias CheckCollection = (item: CollectionUiState) -> Unit
typealias EditCollection = (collectionId: String) -> Unit

class SaveToCollectionAdapter(
    private val onCheckCollection: CheckCollection,
    private val onEditCollection: EditCollection,
) : ListAdapter<CollectionUiState, SaveToCollectionViewHolder>(CollectionDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveToCollectionViewHolder {
        return SaveToCollectionViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SaveToCollectionViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item, onCheckCollection, onEditCollection)
    }

    private object CollectionDiffUtil : DiffUtil.ItemCallback<CollectionUiState>() {
        override fun areItemsTheSame(
            oldItem: CollectionUiState,
            newItem: CollectionUiState,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CollectionUiState,
            newItem: CollectionUiState,
        ): Boolean {
            return oldItem == newItem
        }
    }
}
