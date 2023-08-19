package com.listocalixto.android.rembrandt.core.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.listocalixto.android.rembrandt.core.ui.diffutil.ArtworkDiffUtil
import com.listocalixto.android.rembrandt.core.ui.states.ArtworkUserUiState
import com.listocalixto.android.rembrandt.core.ui.viewholder.ArtworkUserViewHolder

typealias ArtworkClick = (artworkId: Long, container: View, memoryCacheKey: String?, gradientColor: Int) -> Unit
typealias FavoriteClick = (artworkId: Long, isFavorite: Boolean) -> Unit

class ArtworkUserAdapter(
    private val onArtworkClick: ArtworkClick,
    private val onFavoriteClick: FavoriteClick,
) : ListAdapter<ArtworkUserUiState, RecyclerView.ViewHolder>(ArtworkDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ArtworkUserViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ArtworkUserViewHolder)?.apply {
            bind(getItem(position), onArtworkClick, onFavoriteClick)
        }
    }
}
