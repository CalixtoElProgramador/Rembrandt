package com.listocalixto.android.rembrandt.domain.usecase.main

import com.listocalixto.android.rembrandt.domain.entity.Artwork
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ObserveArtworkWithManifestUseCase @Inject constructor(
    private val observeArtworkByIdUseCase: ObserveArtworkByIdUseCase,
    private val getManifestByArtworkId: GetManifestByArtworkIdUseCase,
    private val setManifestByArtwork: SetManifestByArtworkUseCase
) {
    operator fun invoke(id: Long): Flow<Artwork> = flow {
        observeArtworkByIdUseCase(id).collect { resultArtwork ->
            resultArtwork.onSuccess { artwork ->
                emit(artwork)
                if (artwork.manifest == null) {
                    val manifest = getManifestByArtworkId(id)
                    setManifestByArtwork(artwork, manifest)
                }
            }.onFailure {
                throw it
            }
        }
    }
}
