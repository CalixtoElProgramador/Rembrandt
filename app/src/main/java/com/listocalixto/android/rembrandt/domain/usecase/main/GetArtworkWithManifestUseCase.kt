package com.listocalixto.android.rembrandt.domain.usecase.main

import com.listocalixto.android.rembrandt.domain.result.GetArtworkWithManifestResult
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetArtworkWithManifestUseCase @Inject constructor(
    private val getManifestByArtworkId: GetManifestByArtworkIdUseCase,
    private val observeArtworkByIdUseCase: ObserveArtworkByIdUseCase
) {
    operator fun invoke(id: Long): Flow<GetArtworkWithManifestResult> = flow {
        observeArtworkByIdUseCase.invoke(id).collect { resultArtwork ->
            resultArtwork.onSuccess { artwork ->
                val manifest = getManifestByArtworkId(artwork.id)
                val result = GetArtworkWithManifestResult(artwork, manifest)
                emit(result)
            }.onFailure {
                throw it
            }
        }
    }
}
