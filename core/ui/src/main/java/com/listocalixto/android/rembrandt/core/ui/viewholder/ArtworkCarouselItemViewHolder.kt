package com.listocalixto.android.rembrandt.core.ui.viewholder

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import coil.result
import com.listocalixto.android.rembrandt.core.ui.adapter.ArtworkClick
import com.listocalixto.android.rembrandt.core.ui.databinding.ViewArtworkCarouselItemBinding
import com.listocalixto.android.rembrandt.core.ui.extensions.palette
import com.listocalixto.android.rembrandt.core.ui.states.ArtworkUserUiState

class ArtworkCarouselItemViewHolder internal constructor(
    val binding: ViewArtworkCarouselItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: ArtworkUserUiState,
        onArtworkClick: ArtworkClick,
    ) = binding.run {
        artwork = item
        root.setOnClickListener {
            onArtworkClick(
                item.id,
                image,
                image.result?.request?.memoryCacheKey?.key,
                image.drawable?.toBitmap()?.palette()?.darkMutedSwatch?.rgb ?: Color.TRANSPARENT,
            )
        }
        executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ArtworkCarouselItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ViewArtworkCarouselItemBinding.inflate(layoutInflater, parent, false)
            return ArtworkCarouselItemViewHolder(binding)
        }
    }
}
