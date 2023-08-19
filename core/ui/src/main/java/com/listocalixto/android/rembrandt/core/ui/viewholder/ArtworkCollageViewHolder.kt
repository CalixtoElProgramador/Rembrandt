package com.listocalixto.android.rembrandt.core.ui.viewholder

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import coil.result
import com.listocalixto.android.rembrandt.core.ui.adapter.ArtworkClick
import com.listocalixto.android.rembrandt.core.ui.databinding.ViewArtworkCollageBinding
import com.listocalixto.android.rembrandt.core.ui.extensions.palette
import com.listocalixto.android.rembrandt.core.ui.states.ArtworkCollageUiState

internal class ArtworkCollageViewHolder private constructor(
    val binding: ViewArtworkCollageBinding,
) : RecyclerView.ViewHolder(binding.root) {

    inline fun bind(
        item: ArtworkCollageUiState,
        crossinline onArtworkClick: ArtworkClick,
    ) = binding.run {
        artworkCollage = item
        this.onArtworkClick = { artworkId, imageView ->
            onArtworkClick(
                artworkId,
                if (imageView.id == imageTop.id) containerImageTop else imageView,
                imageView.result?.request?.memoryCacheKey?.key,
                imageView.drawable?.toBitmap()?.palette()?.darkMutedSwatch?.rgb
                    ?: Color.TRANSPARENT,
            )
        }
        executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ArtworkCollageViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ViewArtworkCollageBinding.inflate(layoutInflater, parent, false)
            return ArtworkCollageViewHolder(binding)
        }
    }
}
