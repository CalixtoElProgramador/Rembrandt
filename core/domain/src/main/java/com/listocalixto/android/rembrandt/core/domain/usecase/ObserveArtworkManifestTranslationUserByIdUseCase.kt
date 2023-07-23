package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.core.domain.repository.TranslatorRepo
import javax.inject.Inject

class ObserveArtworkManifestTranslationUserByIdUseCase @Inject constructor(
    private val observeArtworkManifestById: ObserveArtworkManifestUserByIdUseCase,
    private val translatorRepo: TranslatorRepo,
)
