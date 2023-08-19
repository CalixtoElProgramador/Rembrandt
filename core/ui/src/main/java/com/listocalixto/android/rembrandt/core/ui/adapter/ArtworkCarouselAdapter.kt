package com.listocalixto.android.rembrandt.core.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.listocalixto.android.rembrandt.core.ui.diffutil.ArtworkDiffUtil
import com.listocalixto.android.rembrandt.core.ui.states.ArtworkUserUiState
import com.listocalixto.android.rembrandt.core.ui.viewholder.ArtworkCarouselItemViewHolder

internal class ArtworkCarouselAdapter(
    private val onArtworkClick: ArtworkClick,
) : ListAdapter<ArtworkUserUiState, ArtworkCarouselItemViewHolder>(ArtworkDiffUtil) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ArtworkCarouselItemViewHolder {
        return ArtworkCarouselItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ArtworkCarouselItemViewHolder, position: Int) {
        holder.bind(currentList[position], onArtworkClick)
    }
}
