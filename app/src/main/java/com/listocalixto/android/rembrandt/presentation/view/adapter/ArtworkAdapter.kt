package com.listocalixto.android.rembrandt.presentation.view.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.listocalixto.android.rembrandt.presentation.ui.main.home.ArtworkUiState
import com.listocalixto.android.rembrandt.presentation.ui.main.home.HomeUiEvent
import com.listocalixto.android.rembrandt.presentation.view.holder.ArtworkViewHolder

class ArtworkAdapter(
    private val onEvent: (event: HomeUiEvent) -> Unit,
    private val onArtwork: (artworkId: Long, container: View, memoryCacheKey: String?, gradientColor: Int) -> Unit,
) : ListAdapter<ArtworkUiState, RecyclerView.ViewHolder>(ArtworkDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ArtworkViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ArtworkViewHolder)?.apply {
            bind(getItem(position), onEvent, onArtwork)
        }
    }

    object ArtworkDiffUtil : DiffUtil.ItemCallback<ArtworkUiState>() {
        override fun areItemsTheSame(oldItem: ArtworkUiState, newItem: ArtworkUiState): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ArtworkUiState, newItem: ArtworkUiState): Boolean {
            return oldItem == newItem
        }
    }
}
