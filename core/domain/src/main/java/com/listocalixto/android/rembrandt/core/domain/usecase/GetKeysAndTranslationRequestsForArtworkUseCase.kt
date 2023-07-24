package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.common.entities.Artwork
import com.listocalixto.android.rembrandt.common.entities.Manifest
import com.listocalixto.android.rembrandt.common.entities.composite.ArtworkUser
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.ArtistDisplay
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.Category
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.Description
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.Title
import javax.inject.Inject

class GetKeysAndTranslationRequestsForArtworkUseCase @Inject constructor() {

    operator fun invoke(
        category: String?,
        title: String?,
        artistDisplay: String?,
        description: String?,
    ): Map<String, String?> {
        return ArtworkTranslationKey.values().associateWith {
            when (it) {
                Category -> category
                Title -> title
                ArtistDisplay -> artistDisplay
                Description -> description
            }
        }.mapKeys { it.key.name }
    }

    operator fun invoke(
        artwork: Artwork,
        manifest: Manifest,
    ): Map<String, String?> {
        val category = artwork.categoryTitles.first()
        val title = artwork.title
        val artistDisplay = artwork.artistDisplay
        val description = manifest.description
        return this(category, title, artistDisplay, description)
    }

    operator fun invoke(
        artworkUser: ArtworkUser,
        manifest: Manifest,
    ): Map<String, String?> {
        return this(Artwork(artworkUser), manifest)
    }
}
