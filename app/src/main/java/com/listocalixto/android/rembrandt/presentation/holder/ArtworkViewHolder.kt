package com.listocalixto.android.rembrandt.presentation.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.listocalixto.android.rembrandt.databinding.ListItemArtworkBinding
import com.listocalixto.android.rembrandt.presentation.ui.main.home.ArtworkUiState
import com.listocalixto.android.rembrandt.presentation.ui.main.home.HomeUiEvent

class ArtworkViewHolder private constructor(
    val binding: ListItemArtworkBinding,
) : RecyclerView.ViewHolder(binding.root) {

    inline fun bind(
        item: ArtworkUiState,
        crossinline onEvent: (event: HomeUiEvent) -> Unit,
    ) = binding.apply {
        artwork = item
        iconButtonFavorite.setOnClickListener { onEvent(HomeUiEvent.ObFavorite(item.id)) }
        iconButtonShare.setOnClickListener { onEvent(HomeUiEvent.OnShare(item.id)) }
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
