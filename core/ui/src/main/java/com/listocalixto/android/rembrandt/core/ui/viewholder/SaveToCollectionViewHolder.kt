package com.listocalixto.android.rembrandt.core.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.listocalixto.android.rembrandt.core.ui.adapter.CheckCollection
import com.listocalixto.android.rembrandt.core.ui.adapter.EditCollection
import com.listocalixto.android.rembrandt.core.ui.databinding.ViewSaveToCollectionBinding
import com.listocalixto.android.rembrandt.core.ui.states.CollectionUiState

class SaveToCollectionViewHolder private constructor(
    private val binding: ViewSaveToCollectionBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: CollectionUiState,
        onCheckCollection: CheckCollection,
        onEditCollection: EditCollection,
    ) = binding.run {
        collection = item
        root.setOnClickListener {
            if (item.isEditModeActivated) {
                onEditCollection(item.id)
            } else {
                onCheckCollection(item)
            }
        }
        iconButtonArrowForward.setOnClickListener { onEditCollection(item.id) }
        checkbox.setOnClickListener { onCheckCollection(item) }
        executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): SaveToCollectionViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ViewSaveToCollectionBinding.inflate(inflater, parent, false)
            return SaveToCollectionViewHolder(binding)
        }
    }
}
