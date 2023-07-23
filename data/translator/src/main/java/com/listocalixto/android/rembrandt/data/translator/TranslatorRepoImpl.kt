package com.listocalixto.android.rembrandt.data.translator

import com.listocalixto.android.rembrandt.common.dependencies.di.Dispatcher
import com.listocalixto.android.rembrandt.common.dependencies.di.RDispatchers.IO
import com.listocalixto.android.rembrandt.common.entities.Translation
import com.listocalixto.android.rembrandt.core.domain.repository.TranslatorRepo
import com.listocalixto.android.rembrandt.data.translator.local.LocalTranslatorDataSource
import com.listocalixto.android.rembrandt.data.translator.remote.RemoteTranslatorDataSource
import kotlinx.coroutines.CoroutineDispatcher
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
        keysAndRequests: Set<Pair<String, String>>,
        targetLanguage: String,
    ): Translation {
        return localDataSource.getTranslationById(id = id) ?: run {
            val keysAndTranslations = mutableMapOf<String, String>()
            withContext(ioDispatcher) {
                keysAndRequests.forEach { pair ->
                    val textTranslated = remoteDataSource.translateText(pair.second, targetLanguage)
                    keysAndTranslations[pair.first] = textTranslated
                }
                Translation(
                    id = id,
                    keysAndTranslations = keysAndTranslations.toMap(),
                ).also {
                    localDataSource.insertTranslation(it)
                }
            }
        }
    }
}
