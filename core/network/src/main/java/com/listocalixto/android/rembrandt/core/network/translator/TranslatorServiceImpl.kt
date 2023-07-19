package com.listocalixto.android.rembrandt.core.network.translator

import io.ktor.client.HttpClient
import javax.inject.Inject

internal class TranslatorServiceImpl @Inject constructor(
    private val client: HttpClient,
) : TranslatorService {

    override suspend fun translateText(text: String, targetLang: String): String {
        return ""
        /*val deeplApiKey = BuildConfig.DEEPL_API_KEY
        val textTranslated: TranslationResponse = client.post(TRANSLATE_URL) {
            contentType(ContentType.parse("application/x-www-form-urlencoded"))
            headers {
                append(HttpHeaders.Authorization, "$AUTHORIZATION_LEY $deeplApiKey")
            }
            parameter(PARAM_TEXT_KEY, text)
            parameter(PARAM_SOURCE_LANG_KEY, PARAM_SOURCE_LANG_VALUE)
            parameter(PARAM_TARGET_LANG_KEY, targetLang)
            parameter(PARAM_FORMALITY_KEY, PARAM_FORMALITY_VALUE)
        }.body()
        val translations = textTranslated.translations
        return if (translations.isEmpty()) EMPTY else translations.first().text*/
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
