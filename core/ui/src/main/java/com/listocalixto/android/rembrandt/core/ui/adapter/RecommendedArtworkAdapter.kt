package com.listocalixto.android.rembrandt.core.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.listocalixto.android.rembrandt.core.ui.states.RecommendedArtworksUiState
import com.listocalixto.android.rembrandt.core.ui.viewholder.RecommendedArtworkViewHolder

class RecommendedArtworkAdapter(
    private val onArtwork: (artworkId: Long, memoryCacheKey: String?, gradientColor: Int) -> Unit,
) : ListAdapter<RecommendedArtworksUiState, RecyclerView.ViewHolder>(ArtworkRecommendedDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecommendedArtworkViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? RecommendedArtworkViewHolder)?.apply {
            bind(getItem(position), onArtwork)
        }
    }

    object ArtworkRecommendedDiffUtil : DiffUtil.ItemCallback<RecommendedArtworksUiState>() {
        override fun areItemsTheSame(
            oldItem: RecommendedArtworksUiState,
            newItem: RecommendedArtworksUiState,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RecommendedArtworksUiState,
            newItem: RecommendedArtworksUiState,
        ): Boolean {
            return oldItem == newItem
        }
    }
}
