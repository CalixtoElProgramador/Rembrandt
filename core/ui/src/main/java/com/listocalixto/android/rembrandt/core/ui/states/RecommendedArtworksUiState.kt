package com.listocalixto.android.rembrandt.core.ui.states

import androidx.annotation.StringRes
import com.listocalixto.android.rembrandt.common.entities.Artwork
import com.listocalixto.android.rembrandt.common.entities.utility.RecommendationType
import com.listocalixto.android.rembrandt.core.ui.R

data class RecommendedArtworksUiState(
    private val artwork: Artwork,
    private val recommendationType: RecommendationType,
) {
    val id: Long = artwork.id
    val imageUrl: String = artwork.imageUrl
    val title: String = artwork.title

    @StringRes
    val reasonItWasRecommendedRes: Int = getReasonItWasRecommended()

    private fun getReasonItWasRecommended() = when (recommendationType) {
        is RecommendationType.SameArtist -> {
            R.string.reason_it_was_recommended_same_artist
        }

        is RecommendationType.SameArtworkType -> {
            R.string.reason_it_was_recommended_same_artwork_type
        }

        is RecommendationType.SameCategory -> {
            R.string.reason_it_was_recommended_same_category
        }

        is RecommendationType.SameGallery -> {
            R.string.reason_it_was_recommended_same_gallery
        }
    }
}
