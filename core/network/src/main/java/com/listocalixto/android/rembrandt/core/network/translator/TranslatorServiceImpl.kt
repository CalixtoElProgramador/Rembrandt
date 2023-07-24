package com.listocalixto.android.rembrandt.core.network.translator

import com.listocalixto.android.rembrandt.core.network.BuildConfig
import com.listocalixto.android.rembrandt.core.network.HttpRoutes.TRANSLATE_URL
import com.listocalixto.android.rembrandt.core.network.translator.response.TranslationResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders.Authorization
import io.ktor.http.contentType
import javax.inject.Inject

internal class TranslatorServiceImpl @Inject constructor(
    private val client: HttpClient,
) : TranslatorService {

    override suspend fun translateText(text: String, targetLanguage: String): String {
        val deeplApiKey = BuildConfig.deeplapikey
        val response: TranslationResponse = client.post(TRANSLATE_URL) {
            contentType(ContentType.Application.FormUrlEncoded)
            headers { append(Authorization, "$AUTHORIZATION_KEY $deeplApiKey") }
            parameter(PARAM_TEXT_KEY, text)
            parameter(PARAM_SOURCE_LANG_KEY, PARAM_SOURCE_LANG_VALUE)
            parameter(PARAM_TARGET_LANG_KEY, targetLanguage)
            parameter(PARAM_FORMALITY_KEY, PARAM_FORMALITY_VALUE)
        }.body()
        val translations = response.translations
        return translations.firstOrNull()?.text.orEmpty()
    }

    private companion object {
        const val PARAM_TEXT_KEY = "text"
        const val PARAM_SOURCE_LANG_KEY = "source_lang"
        const val PARAM_SOURCE_LANG_VALUE = "EN"
        const val PARAM_TARGET_LANG_KEY = "target_lang"
        const val PARAM_FORMALITY_KEY = "formality"
        const val PARAM_FORMALITY_VALUE = "prefer_more"
        const val AUTHORIZATION_KEY = "DeepL-Auth-Key"
    }
}
