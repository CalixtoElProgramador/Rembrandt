package com.listocalixto.android.rembrandt.data.translator.mapper

import com.listocalixto.android.rembrandt.common.dependencies.Mapper
import com.listocalixto.android.rembrandt.common.entities.Translation
import com.listocalixto.android.rembrandt.core.local.entities.translator.LocalTranslation
import javax.inject.Inject

class LocalTranslationToEntity @Inject constructor() : Mapper<LocalTranslation, Translation>() {
    override fun map(input: LocalTranslation) = Translation(
        id = input.id,
        keysAndTranslations = input.keysAndTranslations,
    )

    override fun reverseMap(input: Translation) = LocalTranslation(
        id = input.id,
        keysAndTranslations = input.keysAndTranslations,
    )
}
