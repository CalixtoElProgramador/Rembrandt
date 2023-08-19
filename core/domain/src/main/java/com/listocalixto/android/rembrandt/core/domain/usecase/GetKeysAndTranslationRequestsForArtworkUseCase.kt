package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.common.entities.Artwork
import com.listocalixto.android.rembrandt.common.entities.Manifest
import com.listocalixto.android.rembrandt.common.entities.composite.ArtworkUser
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.ArtistDisplay
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.ArtistTitle
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.ArtworkType
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.Category
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.CreditLine
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.DateDisplay
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.Description
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.Inscriptions
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.MediumDisplay
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.PhysicalDimensions
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.PlaceOfOrigin
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.StyleTitle
import com.listocalixto.android.rembrandt.common.entities.utility.ArtworkTranslationKey.Title
import javax.inject.Inject

class GetKeysAndTranslationRequestsForArtworkUseCase @Inject constructor() {

    operator fun invoke(
        category: String?,
        title: String?,
        artistDisplay: String?,
        description: String?,
        mediumDisplay: String?,
        artworkTypeTitle: String?,
        placeOfOrigin: String?,
        creditLine: String?,
        physicalDimensions: String?,
        artistTitle: String?,
        dateDisplay: String?,
        inscriptions: String?,
        styleTitle: String?,
    ): Map<String, String> {
        val initialMap = ArtworkTranslationKey.values().associateWith { key ->
            when (key) {
                Category -> category
                Title -> title
                ArtistDisplay -> artistDisplay
                Description -> description
                MediumDisplay -> mediumDisplay
                ArtworkType -> artworkTypeTitle
                PlaceOfOrigin -> placeOfOrigin
                CreditLine -> creditLine
                PhysicalDimensions -> physicalDimensions
                ArtistTitle -> artistTitle
                DateDisplay -> dateDisplay
                Inscriptions -> inscriptions
                StyleTitle -> styleTitle
            }
        }.mapKeys { it.key.name }
        val finalMap = mutableMapOf<String, String>()
        initialMap.forEach { (key, value) ->
            if (value != null) {
                finalMap[key] = value
            }
        }
        return finalMap
    }

    operator fun invoke(
        artwork: Artwork,
        manifest: Manifest,
    ): Map<String, String> {
        val category = artwork.categoryTitles.first()
        val title = artwork.title
        val artistDisplay = artwork.artistDisplay
        val description = manifest.description
        val mediumDisplay = artwork.mediumDisplay
        val artworkTypeTitle = artwork.artworkTypeTitle
        val placeOfOrigin = artwork.placeOfOrigin
        val creditLine = artwork.creditLine
        val physicalDimensions = artwork.dimensions
        val artistTitle = artwork.artistTitle
        val dateDisplay = artwork.dateDisplay
        val inscriptions = artwork.inscriptions
        val styleTitle = artwork.styleTitle
        return this(
            category,
            title,
            artistDisplay,
            description,
            mediumDisplay,
            artworkTypeTitle,
            placeOfOrigin,
            creditLine,
            physicalDimensions,
            artistTitle,
            dateDisplay,
            inscriptions,
            styleTitle,
        )
    }

    operator fun invoke(
        artworkUser: ArtworkUser,
        manifest: Manifest,
    ): Map<String, String> {
        return this(Artwork(artworkUser), manifest)
    }
}
