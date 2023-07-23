package com.listocalixto.android.rembrandt.core.network.translator

import com.listocalixto.android.rembrandt.core.network.BuildConfig
import com.listocalixto.android.rembrandt.core.network.HttpRoutes.TRANSLATE_URL
import com.listocalixto.android.rembrandt.core.network.translator.response.TranslationResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.post
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.headers
import javax.inject.Inject

internal class TranslatorServiceImpl @Inject constructor(
    private val client: HttpClient,
) : TranslatorService {

    override suspend fun translateText(text: String, targetLanguage: String): String {
        val deeplApiKey = BuildConfig.deeplapikey
        val response: TranslationResponse = client.post(TRANSLATE_URL) {
            contentType(ContentType.parse("application/x-www-form-urlencoded"))
            headers {
                append(HttpHeaders.Authorization, "$AUTHORIZATION_LEY $deeplApiKey")
            }
            parameter(PARAM_TEXT_KEY, text)
            parameter(PARAM_SOURCE_LANG_KEY, PARAM_SOURCE_LANG_VALUE)
            parameter(PARAM_TARGET_LANG_KEY, targetLanguage)
            parameter(PARAM_FORMALITY_KEY, PARAM_FORMALITY_VALUE)
        }.body()
        val translations = response.translations
        return translations.firstOrNull()?.text.orEmpty()
    }

    companion object {
        private const val PARAM_TEXT_KEY = "text"
        private const val PARAM_SOURCE_LANG_KEY = "source_lang"
        private const val PARAM_SOURCE_LANG_VALUE = "EN"
        private const val PARAM_TARGET_LANG_KEY = "target_lang"
        private const val PARAM_FORMALITY_KEY = "formality"
        private const val PARAM_FORMALITY_VALUE = "prefer_more"
        private const val AUTHORIZATION_LEY = "DeepL-Auth-Key"
    }
}
