package com.listocalixto.android.rembrandt.data.mapper.local

import com.listocalixto.android.rembrandt.core.Mapper
import com.listocalixto.android.rembrandt.data.source.local.implementation.table.main.ManifestTable
import com.listocalixto.android.rembrandt.domain.entity.Manifest
import javax.inject.Inject

class ManifestLocalToEntity @Inject constructor(
    private val metadataMapper: MetadataLocalToModel
) : Mapper<ManifestTable, Manifest> {

    override fun map(value: ManifestTable) = Manifest(
        artworkId = value.artworkId,
        id = value.id,
        description = value.description,
        metadata = metadataMapper.map(value.metadata)
    )

    override fun map(values: List<ManifestTable>) = values.map { map(it) }

    override fun reverseMap(value: Manifest) = ManifestTable(
        artworkId = value.artworkId,
        id = value.id,
        description = value.description.trim().replace("\n", "\n\n"),
        metadata = metadataMapper.reverseMap(value.metadata)
    )

    override fun reverseMap(values: List<Manifest>) = values.map { reverseMap(it) }
}
