package com.listocalixto.android.rembrandt.common.entities.composite

import com.listocalixto.android.rembrandt.common.entities.Artwork
import com.listocalixto.android.rembrandt.common.entities.Manifest
import com.listocalixto.android.rembrandt.common.entities.Translation
import com.listocalixto.android.rembrandt.common.entities.User

data class ArtworkManifestTranslationUser internal constructor(
    val id: Long,
) {

    constructor(artwork: Artwork, manifest: Manifest, translation: Translation, user: User) : this(
        id = artwork.id,
    )
}
