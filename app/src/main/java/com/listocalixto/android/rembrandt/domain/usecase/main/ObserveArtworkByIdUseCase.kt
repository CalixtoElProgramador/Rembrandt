package com.listocalixto.android.rembrandt.domain.usecase.main

import com.listocalixto.android.rembrandt.domain.entity.Artwork
import com.listocalixto.android.rembrandt.domain.repo.ArtworkRepo
import com.listocalixto.android.rembrandt.domain.utility.QualityImageType.MEDIUM
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class ObserveArtworkByIdUseCase @Inject constructor(
    private val repo: ArtworkRepo,
    private val getImageUrl: GetImageUrlUseCase
) {

    operator fun invoke(id: Long): Flow<Result<Artwork>> = flow {
        repo.observeArtworkById(id).collect { result ->
            result.onSuccess { artwork ->
                val artworkUpdated = artwork.copy(
                    imageUrl = getImageUrl(artwork.imageId, qualityType = MEDIUM)
                )
                emit(Result.success(artworkUpdated))
            }.onFailure {
                emit(Result.failure(it))
            }
        }
    }
}
