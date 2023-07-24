package com.listocalixto.android.rembrandt.data.translator

import com.listocalixto.android.rembrandt.common.dependencies.di.Dispatcher
import com.listocalixto.android.rembrandt.common.dependencies.di.RDispatchers.IO
import com.listocalixto.android.rembrandt.common.entities.Translation
import com.listocalixto.android.rembrandt.core.domain.repository.TranslatorRepo
import com.listocalixto.android.rembrandt.data.translator.local.LocalTranslatorDataSource
import com.listocalixto.android.rembrandt.data.translator.remote.RemoteTranslatorDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class TranslatorRepoImpl @Inject constructor(
    private val localDataSource: LocalTranslatorDataSource,
    private val remoteDataSource: RemoteTranslatorDataSource,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) : TranslatorRepo {

    override suspend fun translateText(text: String, targetLanguage: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun getTranslationById(id: String): Translation? {
        return localDataSource.getTranslationById(id)
    }

    override suspend fun getTranslations(
        id: String,
        keysAndRequests: Map<String, String?>,
        targetLanguage: String,
    ): Translation {
        return localDataSource.getTranslationById(id = id) ?: run {
            val keysAndTranslations = mutableMapOf<String, String>()
            val translatedTextsDeferred = mutableListOf<Deferred<String>>()
            withContext(ioDispatcher) {
                keysAndRequests.values.forEach { text ->
                    text?.let {
                        val deferredText =
                            async { remoteDataSource.translateText(text, targetLanguage) }
                        translatedTextsDeferred.add(deferredText)
                    }
                }
                keysAndRequests.keys.forEachIndexed { index, key ->
                    keysAndTranslations[key] = translatedTextsDeferred[index].await()
                }
                Translation(id = id, keysAndTranslations = keysAndTranslations).also {
                    localDataSource.insertTranslation(it)
                }
            }
        }
    }
}
