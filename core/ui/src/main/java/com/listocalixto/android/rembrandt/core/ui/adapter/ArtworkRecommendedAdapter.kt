package com.listocalixto.android.rembrandt.core.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.listocalixto.android.rembrandt.core.ui.states.ArtworkRecommendedUiState
import com.listocalixto.android.rembrandt.core.ui.viewholder.ArtworkRecommendedViewHolder

class ArtworkRecommendedAdapter(
    private val onArtwork: (artworkId: Long, memoryCacheKey: String?, gradientColor: Int) -> Unit,
) : ListAdapter<ArtworkRecommendedUiState, RecyclerView.ViewHolder>(ArtworkRecommendedDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ArtworkRecommendedViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ArtworkRecommendedViewHolder)?.apply {
            bind(getItem(position), onArtwork)
        }
    }

    object ArtworkRecommendedDiffUtil : DiffUtil.ItemCallback<ArtworkRecommendedUiState>() {
        override fun areItemsTheSame(
            oldItem: ArtworkRecommendedUiState,
            newItem: ArtworkRecommendedUiState,
        ): Boolean {
            return oldItem.imageUrl == newItem.imageUrl
        }

        override fun areContentsTheSame(
            oldItem: ArtworkRecommendedUiState,
            newItem: ArtworkRecommendedUiState,
        ): Boolean {
            return oldItem == newItem
        }
    }
}
