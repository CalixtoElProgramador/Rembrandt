package com.listocalixto.android.rembrandt.core.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.listocalixto.android.rembrandt.core.ui.adapter.ArtworkCarouselAdapter
import com.listocalixto.android.rembrandt.core.ui.adapter.ArtworkClick
import com.listocalixto.android.rembrandt.core.ui.databinding.ViewArtworkCarouselBinding
import com.listocalixto.android.rembrandt.core.ui.states.ArtworkCarouselUiState

class ArtworkCarouselViewHolder private constructor(
    val binding: ViewArtworkCarouselBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: ArtworkCarouselUiState,
        onArtworkClick: ArtworkClick,
    ) = binding.run {
        ArtworkCarouselAdapter(onArtworkClick).also {
            it.submitList(item.artworks)
            carousel.adapter = it
        }
        executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ArtworkCarouselViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ViewArtworkCarouselBinding.inflate(layoutInflater, parent, false)
            return ArtworkCarouselViewHolder(binding)
        }
    }
}
