package com.listocalixto.android.rembrandt.presentation.view.holder

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import coil.result
import com.listocalixto.android.rembrandt.databinding.ListItemArtworkBinding
import com.listocalixto.android.rembrandt.presentation.ui.main.home.ArtworkUiState
import com.listocalixto.android.rembrandt.presentation.ui.main.home.HomeUiEvent
import com.listocalixto.android.rembrandt.presentation.utility.extentions.palette

class ArtworkViewHolder private constructor(
    val binding: ListItemArtworkBinding,
) : RecyclerView.ViewHolder(binding.root) {

    inline fun bind(
        item: ArtworkUiState,
        crossinline onEvent: (event: HomeUiEvent) -> Unit,
        crossinline onArtwork: (artworkId: Long, container: View, memoryCacheKey: String?, gradientColor: Int) -> Unit,
    ) = binding.apply {
        artwork = item
        iconButtonFavorite.setOnClickListener { onEvent(HomeUiEvent.ObFavorite(item.id)) }
        iconButtonShare.setOnClickListener { onEvent(HomeUiEvent.OnShare(item.id)) }
        root.setOnClickListener {
            onArtwork(
                item.id,
                card,
                image.result?.request?.memoryCacheKey?.key,
                image?.drawable?.toBitmap()?.palette()?.darkMutedSwatch?.rgb ?: Color.TRANSPARENT,
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
