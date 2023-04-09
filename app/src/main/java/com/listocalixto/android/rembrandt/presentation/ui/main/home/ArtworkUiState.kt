package com.listocalixto.android.rembrandt.presentation.ui.main.home

import com.listocalixto.android.rembrandt.domain.entity.Artwork

data class ArtworkUiState(
    val id: Long = -1,
    val imageUrl: String = "",
    val type: String = "",
    val caption: String = "",
    val title: String = "",
    val artistName: String = "",
    val isFavorite: Boolean
) {

    companion object {
        fun domainToUiState(artwork: Artwork) = ArtworkUiState(
            id = artwork.id,
            imageUrl = artwork.imageUrl,
            type = artwork.artworkTypeTitle,
            caption = if (artwork.categoryTitles.isEmpty()) "IS EMPTY" else artwork.categoryTitles.first(),
            title = artwork.title,
            artistName = artwork.artistTitle,
            isFavorite = artwork.isFavorite
        )
    }
}
