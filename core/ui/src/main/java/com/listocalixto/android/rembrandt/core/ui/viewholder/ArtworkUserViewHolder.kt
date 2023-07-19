package com.listocalixto.android.rembrandt.core.ui.viewholder

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import coil.result
import com.listocalixto.android.rembrandt.core.ui.adapter.ArtworkClick
import com.listocalixto.android.rembrandt.core.ui.adapter.FavoriteClick
import com.listocalixto.android.rembrandt.core.ui.databinding.ViewArtworkUserBinding
import com.listocalixto.android.rembrandt.core.ui.extensions.palette
import com.listocalixto.android.rembrandt.core.ui.states.ArtworkUserUiState

class ArtworkUserViewHolder private constructor(
    val binding: ViewArtworkUserBinding,
) : RecyclerView.ViewHolder(binding.root) {

    inline fun bind(
        item: ArtworkUserUiState,
        crossinline onArtworkClick: ArtworkClick,
        crossinline onFavoriteClick: FavoriteClick,
    ) = binding.apply {
        artwork = item
        iconButtonFavorite.setOnClickListener { onFavoriteClick(item.id, item.isFavorite) }
        iconButtonShare.setOnClickListener {}
        root.setOnClickListener {
            onArtworkClick(
                item.id,
                card,
                image.result?.request?.memoryCacheKey?.key,
                image.drawable?.toBitmap()?.palette()?.darkMutedSwatch?.rgb ?: Color.TRANSPARENT,
            )
        }
        executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ArtworkUserViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ViewArtworkUserBinding.inflate(layoutInflater, parent, false)
            return ArtworkUserViewHolder(binding)
        }
    }
}
