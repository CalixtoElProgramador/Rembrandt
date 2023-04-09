package com.listocalixto.android.rembrandt.domain.usecase.main

import com.listocalixto.android.rembrandt.domain.repo.ArtworkRepo
import com.listocalixto.android.rembrandt.domain.result.GetArtworksByPageResult
import com.listocalixto.android.rembrandt.domain.utility.QualityImageType.MEDIUM
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetArtworksByPageUseCase @Inject constructor(
    private val repo: ArtworkRepo,
    private val getImageUrl: GetImageUrlUseCase
) {
    operator fun invoke(page: Int): Flow<Result<GetArtworksByPageResult>> = flow {
        val pageString = page.toString()
        repo.observeArtworksByPage(pageString).collect { result ->
            result.onSuccess { artworks ->
                val artworksWithImages = artworks.map { artwork ->
                    artwork.copy(imageUrl = getImageUrl(artwork.imageId, qualityType = MEDIUM))
                }.toSet()
                emit(Result.success(GetArtworksByPageResult(artworksWithImages)))
            }.onFailure {
                emit(Result.failure(exception = it))
            }
        }
    }
}
