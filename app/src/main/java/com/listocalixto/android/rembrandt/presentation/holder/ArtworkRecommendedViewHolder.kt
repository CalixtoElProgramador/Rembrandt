package com.listocalixto.android.rembrandt.presentation.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.listocalixto.android.rembrandt.databinding.ListItemRecommendedBinding
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.ArtworkRecommendedUiState

class ArtworkRecommendedViewHolder private constructor(
    val binding: ListItemRecommendedBinding,
) : RecyclerView.ViewHolder(binding.root) {

    inline fun bind(item: ArtworkRecommendedUiState, crossinline onArtwork: () -> Unit) =
        binding.run {
            artwork = item
            root.setOnClickListener { onArtwork() }
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
