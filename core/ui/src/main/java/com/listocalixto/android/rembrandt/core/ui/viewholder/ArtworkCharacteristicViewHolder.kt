package com.listocalixto.android.rembrandt.core.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.listocalixto.android.rembrandt.core.ui.databinding.ViewArtworkCharacteristicBinding
import com.listocalixto.android.rembrandt.core.ui.utility.UiText

class ArtworkCharacteristicViewHolder private constructor(
    val binding: ViewArtworkCharacteristicBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(@StringRes label: Int, value: UiText) = binding.run {
        this.label = label
        this.value = value
        executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ArtworkCharacteristicViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ViewArtworkCharacteristicBinding.inflate(layoutInflater, parent, false)
            return ArtworkCharacteristicViewHolder(binding)
        }
    }
}
