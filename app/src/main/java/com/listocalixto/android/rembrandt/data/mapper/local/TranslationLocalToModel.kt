package com.listocalixto.android.rembrandt.data.mapper.local

import com.listocalixto.android.rembrandt.core.Mapper
import com.listocalixto.android.rembrandt.data.source.local.implementation.model.TranslationLocal
import com.listocalixto.android.rembrandt.domain.model.Translation
import javax.inject.Inject

class TranslationLocalToModel @Inject constructor() : Mapper<TranslationLocal, Translation> {

    override fun map(value: TranslationLocal) = Translation(
        category = value.category,
        title = value.title,
        content = value.content
    )

    override fun map(values: List<TranslationLocal>) = values.map { map(it) }

    override fun reverseMap(value: Translation) = TranslationLocal(
        category = value.category,
        title = value.title,
        content = value.content
    )

    override fun reverseMap(values: List<Translation>) = values.map { reverseMap(it) }
}
