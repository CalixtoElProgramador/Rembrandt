package com.listocalixto.android.rembrandt.data.translator.remote

import com.listocalixto.android.rembrandt.core.network.translator.TranslatorService
import javax.inject.Inject

internal class RemoteTranslatorDataSourceImpl @Inject constructor(
    private val service: TranslatorService,
) : RemoteTranslatorDataSource {

    override suspend fun translateText(text: String, targetLanguage: String): String {
        return service.translateText(text, targetLanguage)
    }
}
