package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.common.entities.Artwork
import com.listocalixto.android.rembrandt.core.domain.repository.ArtworkRepo
import com.listocalixto.android.rembrandt.core.domain.utility.QualityImageType.High
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ObserveArtworkByIdUseCase @Inject constructor(
    private val repo: ArtworkRepo,
    private val getImageUrl: GetImageUrlUseCase,
) {
    /*operator fun invoke(id: Long): Flow<Result<Artwork>> = flow {
        repo.observeArtworkById(id).collect { result ->
            result.onSuccess { artwork ->
                val artworkUpdated = artwork.copy(
                    imageUrl = getImageUrl(artwork.imageId, qualityType = High),
                )
                emit(Result.success(artworkUpdated))
            }.onFailure {
                emit(Result.failure(it))
            }
        }
    }*/
}
