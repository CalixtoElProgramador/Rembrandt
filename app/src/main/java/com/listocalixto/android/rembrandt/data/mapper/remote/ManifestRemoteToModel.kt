package com.listocalixto.android.rembrandt.data.mapper.remote

import com.listocalixto.android.rembrandt.core.Mapper
import com.listocalixto.android.rembrandt.data.source.remote.implementation.model.ManifestRemote
import com.listocalixto.android.rembrandt.domain.model.Manifest
import javax.inject.Inject

class ManifestRemoteToModel @Inject constructor(
    private val metadataMapper: MetadataRemoteToModel
) : Mapper<ManifestRemote, Manifest> {

    override fun map(value: ManifestRemote) = Manifest(
        artworkId = value.artworkId,
        id = value.id,
        description = value.description,
        metadata = metadataMapper.map(value.metadata)
    )

    override fun map(values: List<ManifestRemote>) = values.map { map(it) }

    override fun reverseMap(value: Manifest) = ManifestRemote(
        artworkId = value.artworkId,
        id = value.id,
        description = value.description,
        metadata = metadataMapper.reverseMap(value.metadata)
    )

    override fun reverseMap(values: List<Manifest>) = values.map { reverseMap(it) }
}
