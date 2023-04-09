package com.listocalixto.android.rembrandt.data.mapper.remote

import com.listocalixto.android.rembrandt.core.Mapper
import com.listocalixto.android.rembrandt.data.source.remote.implementation.model.MetadataRemote
import com.listocalixto.android.rembrandt.domain.model.Metadata as OwnMetadata
import javax.inject.Inject

class MetadataRemoteToModel @Inject constructor() : Mapper<MetadataRemote, OwnMetadata> {

    override fun map(value: MetadataRemote) = OwnMetadata(
        label = value.label,
        value = value.value
    )

    override fun map(values: List<MetadataRemote>) = values.map { map(it) }

    override fun reverseMap(value: OwnMetadata) = MetadataRemote(
        label = value.label,
        value = value.value
    )

    override fun reverseMap(values: List<OwnMetadata>) = values.map { reverseMap(it) }
}
