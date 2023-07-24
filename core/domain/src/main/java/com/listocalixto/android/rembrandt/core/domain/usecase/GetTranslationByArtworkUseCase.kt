package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.common.entities.Artwork
import com.listocalixto.android.rembrandt.common.entities.Manifest
import com.listocalixto.android.rembrandt.common.entities.Translation
import com.listocalixto.android.rembrandt.common.entities.composite.ArtworkUser
import com.listocalixto.android.rembrandt.core.domain.utility.TranslationFromType
import javax.inject.Inject

class GetTranslationByArtworkUseCase @Inject constructor(
    private val getManifestByArtworkId: GetManifestByArtworkIdUseCase,
    private val getKeysAndTranslationRequestsForArtwork: GetKeysAndTranslationRequestsForArtworkUseCase,
    private val getTranslation: GetTranslationsUseCase,
) {
    suspend operator fun invoke(artwork: Artwork, manifest: Manifest?): Translation {
        val artworkManifest = manifest ?: getManifestByArtworkId(artwork.id)
        val keysAndRequests = getKeysAndTranslationRequestsForArtwork(artwork, artworkManifest)
        return getTranslation(artwork.id, fromType = TranslationFromType.Artwork, keysAndRequests)
    }

    suspend operator fun invoke(artworkUser: ArtworkUser, manifest: Manifest?): Translation {
        return this(Artwork(artworkUser), manifest)
    }
}
