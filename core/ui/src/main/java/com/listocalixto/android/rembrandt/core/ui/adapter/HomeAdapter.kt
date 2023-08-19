package com.listocalixto.android.rembrandt.core.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.listocalixto.android.rembrandt.core.ui.states.ArtworkCarouselUiState
import com.listocalixto.android.rembrandt.core.ui.states.ArtworkCollageUiState
import com.listocalixto.android.rembrandt.core.ui.states.ArtworkUserUiState
import com.listocalixto.android.rembrandt.core.ui.states.HomeItem
import com.listocalixto.android.rembrandt.core.ui.viewholder.ArtworkCarouselViewHolder
import com.listocalixto.android.rembrandt.core.ui.viewholder.ArtworkCollageViewHolder
import com.listocalixto.android.rembrandt.core.ui.viewholder.ArtworkUserViewHolder

class HomeAdapter(
    private val onArtworkClick: ArtworkClick,
    private val onFavoriteClick: FavoriteClick,
) : ListAdapter<HomeItem, RecyclerView.ViewHolder>(HomeItemDiffUtil) {

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {
        return when (currentList[position]) {
            is ArtworkCollageUiState -> ArtworkCollageViewHolder.from(parent)
            is ArtworkUserUiState -> ArtworkUserViewHolder.from(parent)
            is ArtworkCarouselUiState -> ArtworkCarouselViewHolder.from(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = currentList[position]) {
            is ArtworkCollageUiState -> {
                (holder as? ArtworkCollageViewHolder)?.apply {
                    bind(item, onArtworkClick)
                }
            }

            is ArtworkUserUiState -> {
                (holder as? ArtworkUserViewHolder)?.apply {
                    bind(item, onArtworkClick, onFavoriteClick)
                }
            }

            is ArtworkCarouselUiState -> {
                (holder as? ArtworkCarouselViewHolder)?.apply {
                    bind(item, onArtworkClick)
                }
            }
        }
    }

    object HomeItemDiffUtil : DiffUtil.ItemCallback<HomeItem>() {
        override fun areItemsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
            return oldItem == newItem
        }
    }
}
