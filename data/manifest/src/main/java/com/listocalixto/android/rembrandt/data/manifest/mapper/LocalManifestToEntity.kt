package com.listocalixto.android.rembrandt.data.manifest.mapper

import com.listocalixto.android.rembrandt.common.dependencies.Mapper
import com.listocalixto.android.rembrandt.common.entities.Manifest
import com.listocalixto.android.rembrandt.core.local.entities.manifest.LocalManifest
import javax.inject.Inject

class LocalManifestToEntity @Inject constructor() : Mapper<LocalManifest, Manifest>() {

    override fun map(input: LocalManifest) = Manifest(
        id = input.id,
        description = input.description,
        attribution = input.attribution,
        logoAttribution = input.logoAttribution,
    )

    override fun reverseMap(input: Manifest) = LocalManifest(
        id = input.id,
        description = input.description,
        attribution = input.attribution,
        logoAttribution = input.logoAttribution,
    )
}
