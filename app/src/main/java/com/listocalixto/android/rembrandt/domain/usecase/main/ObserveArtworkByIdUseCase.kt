package com.listocalixto.android.rembrandt.domain.usecase.main

import com.listocalixto.android.rembrandt.domain.entity.Artwork
import com.listocalixto.android.rembrandt.domain.repo.ArtworkRepo
import com.listocalixto.android.rembrandt.domain.utility.QualityImageType.MEDIUM
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ObserveArtworkByIdUseCase @Inject constructor(
    private val repo: ArtworkRepo,
    private val getImageUrl: GetImageUrlUseCase,
) {

    operator fun invoke(id: Long): Flow<Result<Artwork>> = flow {
        val result = repo.observeArtworkById(id).first()
        result.onSuccess { artwork ->
            val artworkUpdated = artwork.copy(imageUrl = getImageUrl(artwork.imageId, MEDIUM))
            emit(Result.success(artworkUpdated))
        }.onFailure {
            emit(Result.failure(it))
        }
    }
}
