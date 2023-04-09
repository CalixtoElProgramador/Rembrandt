package com.listocalixto.android.rembrandt.data.mapper.local

import com.listocalixto.android.rembrandt.core.Mapper
import com.listocalixto.android.rembrandt.data.source.local.implementation.model.MetadataLocal
import com.listocalixto.android.rembrandt.domain.model.Metadata as DomainMetadata
import javax.inject.Inject

class MetadataLocalToModel @Inject constructor() : Mapper<MetadataLocal, DomainMetadata> {

    override fun map(value: MetadataLocal) = DomainMetadata(
        label = value.label,
        value = value.value
    )

    override fun map(values: List<MetadataLocal>) = values.map { map(it) }

    override fun reverseMap(value: DomainMetadata) = MetadataLocal(
        label = value.label,
        value = value.value
    )

    override fun reverseMap(values: List<DomainMetadata>) = values.map { reverseMap(it) }
}
