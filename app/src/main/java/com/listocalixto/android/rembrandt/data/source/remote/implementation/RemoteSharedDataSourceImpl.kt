package com.listocalixto.android.rembrandt.data.source.remote.implementation

import com.listocalixto.android.rembrandt.BuildConfig
import com.listocalixto.android.rembrandt.core.Constants.EMPTY
import com.listocalixto.android.rembrandt.data.source.remote.configuration.HttpRoutes.TRANSLATE_URL
import com.listocalixto.android.rembrandt.data.source.remote.implementation.response.shared.TranslationResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import javax.inject.Inject

class RemoteSharedDataSourceImpl @Inject constructor(
    private val client: HttpClient
) : RemoteSharedDataSource {

    override suspend fun translateText(text: String, targetLang: String): String {
        val deeplApiKey = BuildConfig.DEEPL_API_KEY
        val textTranslated: TranslationResponse = client.post(TRANSLATE_URL) {
            contentType(ContentType.parse("application/x-www-form-urlencoded"))
            headers {
                append(HttpHeaders.Authorization, "$AUTHORIZATION_LEY $deeplApiKey")
            }
            parameter(PARAM_TEXT_KEY, text)
            parameter(PARAM_SOURCE_LANG_KEY, PARAM_SOURCE_LANG_VALUE)
            parameter(PARAM_TARGET_LANG_KEY, targetLang)
        }.body()
        val translations = textTranslated.translations
        return if (translations.isEmpty()) EMPTY else translations.first().text
    }

    companion object {
        private const val PARAM_TEXT_KEY = "text"
        private const val PARAM_SOURCE_LANG_KEY = "source_lang"
        private const val PARAM_SOURCE_LANG_VALUE = "EN"
        private const val PARAM_TARGET_LANG_KEY = "target_lang"
        private const val AUTHORIZATION_LEY = "DeepL-Auth-Key"
    }
}
