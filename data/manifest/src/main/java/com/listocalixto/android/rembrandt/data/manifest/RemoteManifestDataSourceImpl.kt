package com.listocalixto.android.rembrandt.data.manifest

class RemoteManifestDataSourceImpl {

    /*override suspend fun fetchManifestByArtworkId(id: Long): Manifest {
        val response: GetManifestResponse =
            client.get("https://api.artic.edu/api/v1/artworks/$id/manifest.json").body()

        val descriptions = response.description
        val manifestRemote = ManifestRemote(
            artworkId = id,
            id = UUID.randomUUID().toString(),
            description = if (descriptions.isEmpty()) Constants.EMPTY else descriptions.first().value,
            metadata = response.metadata,
        )
        return manifestMapper.map(manifestRemote)
    }*/
}
