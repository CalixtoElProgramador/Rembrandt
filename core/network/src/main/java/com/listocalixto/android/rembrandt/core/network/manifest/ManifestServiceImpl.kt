package com.listocalixto.android.rembrandt.core.network.manifest

import com.listocalixto.android.rembrandt.core.network.HttpRoutes.ARTWORK
import com.listocalixto.android.rembrandt.core.network.manifest.response.RemoteManifest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path
import javax.inject.Inject

internal class ManifestServiceImpl @Inject constructor(
    private val client: HttpClient,
) : ManifestService {
    override suspend fun getManifestByArtworkId(id: Long): RemoteManifest {
        val response: RemoteManifest = client.get(ARTWORK) {
            url {
                path(id.toString())
                path(MANIFEST_PATH)
            }
        }.body()
        return response
    }

    companion object {
        private const val MANIFEST_PATH = "manifest.json"
    }
}
