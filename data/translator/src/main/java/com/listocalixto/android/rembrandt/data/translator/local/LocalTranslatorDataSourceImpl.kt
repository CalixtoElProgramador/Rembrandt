package com.listocalixto.android.rembrandt.data.translator.local

import com.listocalixto.android.rembrandt.common.dependencies.di.Dispatcher
import com.listocalixto.android.rembrandt.common.dependencies.di.RDispatchers.Default
import com.listocalixto.android.rembrandt.common.entities.Translation
import com.listocalixto.android.rembrandt.core.local.translator.TranslatorDao
import com.listocalixto.android.rembrandt.data.translator.mapper.LocalTranslationToEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class LocalTranslatorDataSourceImpl @Inject constructor(
    private val dao: TranslatorDao,
    private val mapper: LocalTranslationToEntity,
    @Dispatcher(Default) private val defaultDispatcher: CoroutineDispatcher,
) : LocalTranslatorDataSource {
    override suspend fun getTranslationById(id: String): Translation? {
        val localTranslation = dao.getTranslationById(id)
        return localTranslation.firstOrNull()?.let {
            withContext(defaultDispatcher) { mapper.map(it) }
        }
    }

    override suspend fun insertTranslation(item: Translation) {
        val localTranslation = withContext(defaultDispatcher) { mapper.reverseMap(item) }
        dao.insertTranslation(localTranslation)
    }

    override suspend fun deleteAllTranslations() {
        dao.deleteAllTranslations()
    }

    override suspend fun deleteTranslationById(id: String) {
        dao.deleteTranslationById(id)
    }
}
