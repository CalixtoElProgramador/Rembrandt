package com.listocalixto.android.rembrandt.core.network.manifest

import com.listocalixto.android.rembrandt.core.network.manifest.response.RemoteManifest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

internal class ManifestServiceImpl @Inject constructor(
    private val client: HttpClient,
) : ManifestService {
    override suspend fun getManifestByArtworkId(id: Long): RemoteManifest {
        return client.get("https://api.artic.edu/api/v1/artworks/$id/manifest.json").body()
    }

    companion object {
        private const val MANIFEST_PATH = "manifest.json"
    }
}
