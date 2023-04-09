package com.listocalixto.android.rembrandt.data.mapper.local

import com.listocalixto.android.rembrandt.core.Mapper
import com.listocalixto.android.rembrandt.data.source.local.implementation.model.ManifestLocal
import com.listocalixto.android.rembrandt.domain.model.Manifest
import javax.inject.Inject

class ManifestLocalToModel @Inject constructor(
    private val metadataMapper: MetadataLocalToModel
) : Mapper<ManifestLocal, Manifest> {

    override fun map(value: ManifestLocal) = Manifest(
        artworkId = value.artworkId,
        id = value.id,
        description = value.description,
        metadata = metadataMapper.map(value.metadata)
    )

    override fun map(values: List<ManifestLocal>) = values.map { map(it) }

    override fun reverseMap(value: Manifest) = ManifestLocal(
        artworkId = value.artworkId,
        id = value.id,
        description = value.description.trim(),
        metadata = metadataMapper.reverseMap(value.metadata)
    )

    override fun reverseMap(values: List<Manifest>) = values.map { reverseMap(it) }
}
