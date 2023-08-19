package com.listocalixto.android.rembrandt.core.ui.viewholder

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import coil.result
import com.listocalixto.android.rembrandt.core.ui.databinding.ViewArtworkRecommendedBinding
import com.listocalixto.android.rembrandt.core.ui.extensions.palette
import com.listocalixto.android.rembrandt.core.ui.states.RecommendedArtworksUiState

internal class RecommendedArtworkViewHolder private constructor(
    val binding: ViewArtworkRecommendedBinding,
) : RecyclerView.ViewHolder(binding.root) {

    inline fun bind(
        item: RecommendedArtworksUiState,
        crossinline onArtwork: (artworkId: Long, memoryCacheKey: String?, gradientColor: Int) -> Unit,
    ) = binding.run {
        artwork = item
        root.setOnClickListener {
            onArtwork(
                item.id,
                image.result?.request?.memoryCacheKey?.key,
                image.drawable?.toBitmap()?.palette()?.darkMutedSwatch?.rgb ?: Color.TRANSPARENT,
            )
        }
        executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): RecommendedArtworkViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ViewArtworkRecommendedBinding.inflate(inflater, parent, false)
            return RecommendedArtworkViewHolder(binding)
        }
    }
}
