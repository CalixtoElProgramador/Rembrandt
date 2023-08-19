package com.listocalixto.android.rembrandt.core.ui.states

import com.listocalixto.android.rembrandt.common.entities.composite.ArtworkUser
import com.listocalixto.android.rembrandt.core.ui.R
import com.listocalixto.android.rembrandt.core.ui.utility.UiText

data class ArtworkUserUiState internal constructor(
    override val id: Long = -1,
    val imageUrl: String = "",
    val type: String = "",
    val caption: UiText = UiText.StringResource(R.string.uncategorized),
    val title: String = "",
    val artistName: UiText = UiText.StringResource(R.string.unknown_artist),
    val isFavorite: Boolean,
    val altText: String,
) : HomeItem {
    constructor(artworkUser: ArtworkUser) : this(
        id = artworkUser.id,
        imageUrl = artworkUser.imageUrl,
        type = artworkUser.artworkTypeTitle,
        caption = if (artworkUser.categoryTitles.isEmpty()) {
            UiText.StringResource(R.string.uncategorized)
        } else {
            UiText.StringValue(
                artworkUser.categoryTitles.first(),
            )
        },
        title = artworkUser.title,
        artistName = artworkUser.artistTitle?.let { UiText.StringValue(it) }
            ?: UiText.StringResource(R.string.unknown_artist),
        isFavorite = artworkUser.isFavorite,
        altText = artworkUser.thumbnail.altText,
    )
}
