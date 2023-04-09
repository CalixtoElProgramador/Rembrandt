package com.listocalixto.android.rembrandt.data.source.remote.implementation

import com.listocalixto.android.rembrandt.core.Constants.EMPTY
import com.listocalixto.android.rembrandt.data.mapper.remote.ManifestResponseToEntity
import com.listocalixto.android.rembrandt.data.source.remote.implementation.model.ManifestRemote
import com.listocalixto.android.rembrandt.data.source.remote.implementation.response.main.GetManifestResponse
import com.listocalixto.android.rembrandt.domain.entity.Manifest
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import java.util.UUID
import javax.inject.Inject

class RemoteManifestDataSourceImpl @Inject constructor(
    private val mapper: ManifestResponseToEntity,
    private val client: HttpClient
) : RemoteManifestDataSource {

    override suspend fun fetchManifestByArtworkId(id: Long): Manifest {
        val response: GetManifestResponse =
            client.get("https://api.artic.edu/api/v1/artworks/$id/manifest.json")

        val descriptions = response.description
        val manifestRemote = ManifestRemote(
            artworkId = id,
            id = UUID.randomUUID().toString(),
            description = if (descriptions.isEmpty()) EMPTY else descriptions.first().value,
            metadata = response.metadata
        )
        return mapper.map(manifestRemote)
    }
}
