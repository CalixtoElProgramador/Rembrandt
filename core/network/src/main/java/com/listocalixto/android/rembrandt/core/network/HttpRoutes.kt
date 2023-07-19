package com.listocalixto.android.rembrandt.core.network

object HttpRoutes {
    private const val BASE_URL = "https://api.artic.edu/api/v1"
    const val ARTWORKS = "$BASE_URL/artworks/search"
    const val ARTWORK = "$BASE_URL/artworks"
    const val TRANSLATE_URL = "https://api-free.deepl.com/v2/translate"
}
