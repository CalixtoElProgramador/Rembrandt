package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.common.entities.Translation
import com.listocalixto.android.rembrandt.core.domain.repository.TranslatorRepo
import javax.inject.Inject

class GetTranslationByIdUseCase @Inject constructor(
    private val repo: TranslatorRepo,
) {
    suspend operator fun invoke(id: String): Translation? {
        return repo.getTranslationById(id)
    }
}
