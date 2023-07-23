package com.listocalixto.android.rembrandt.data.manifest.mapper

import com.listocalixto.android.rembrandt.common.dependencies.Mapper
import com.listocalixto.android.rembrandt.common.entities.Manifest
import com.listocalixto.android.rembrandt.core.network.manifest.response.RemoteManifest
import javax.inject.Inject

class RemoteManifestToEntity @Inject constructor() : Mapper<RemoteManifest, Manifest>() {

    override fun map(input: RemoteManifest) = Manifest(
        id = input.id,
        description = input.description.first().value,
        attribution = input.attribution,
        logoAttribution = input.logo,
    )

    override fun reverseMap(input: Manifest): RemoteManifest {
        throw Exception()
    }
}
