package com.listocalixto.android.rembrandt.presentation.view.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.result
import com.listocalixto.android.rembrandt.databinding.ListItemArtworkBinding
import com.listocalixto.android.rembrandt.presentation.ui.main.home.ArtworkUiState
import com.listocalixto.android.rembrandt.presentation.ui.main.home.HomeUiEvent

class ArtworkViewHolder private constructor(
    val binding: ListItemArtworkBinding
) : RecyclerView.ViewHolder(binding.root) {

    inline fun bind(
        item: ArtworkUiState,
        crossinline onEvent: (event: HomeUiEvent) -> Unit,
        crossinline onArtwork: (artworkId: Long, container: View, memoryCacheKey: String?) -> Unit
    ) = binding.apply {
        artwork = item
        iconButtonFavorite.setOnClickListener { onEvent(HomeUiEvent.ObFavorite(item.id)) }
        iconButtonShare.setOnClickListener { onEvent(HomeUiEvent.OnShare(item.id)) }
        root.setOnClickListener {
            onArtwork(
                item.id,
                card,
                image.result?.request?.memoryCacheKey?.key
            )
        }
        executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ArtworkViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemArtworkBinding.inflate(layoutInflater, parent, false)
            return ArtworkViewHolder(binding)
        }
    }
}
