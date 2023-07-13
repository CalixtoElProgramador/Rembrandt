package com.listocalixto.android.rembrandt.presentation.view.holder

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import coil.result
import com.listocalixto.android.rembrandt.databinding.ListItemRecommendedBinding
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.ArtworkRecommendedUiState
import com.listocalixto.android.rembrandt.presentation.utility.extentions.palette

class ArtworkRecommendedViewHolder private constructor(
    val binding: ListItemRecommendedBinding,
) : RecyclerView.ViewHolder(binding.root) {

    inline fun bind(
        item: ArtworkRecommendedUiState,
        crossinline onArtwork: (artworkId: Long, memoryCacheKey: String?, gradientColor: Int) -> Unit,
    ) = binding.run {
        artwork = item
        root.setOnClickListener {
            onArtwork(
                item.id,
                image.result?.request?.memoryCacheKey?.key,
                image.drawable.toBitmap().palette().darkMutedSwatch?.rgb ?: Color.TRANSPARENT,
            )
        }
        executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ArtworkRecommendedViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListItemRecommendedBinding.inflate(inflater, parent, false)
            return ArtworkRecommendedViewHolder(binding)
        }
    }
}
