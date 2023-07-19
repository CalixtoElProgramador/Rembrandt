package com.listocalixto.android.rembrandt.core.domain.usecase

import com.listocalixto.android.rembrandt.core.domain.repository.ArtworkRepo
import javax.inject.Inject

class GetArtworksByPageUseCase @Inject constructor(
    private val repo: ArtworkRepo,
    private val getImageUrl: GetImageUrlUseCase,
) {
    /*operator fun invoke(page: Int): Flow<Result<GetArtworksByPageResult>> = flow {
        val pageString = page.toString()
        repo.observeArtworksByPage(pageString).collect { result ->
            result.onSuccess { artworks ->
                val artworksWithImages = artworks.map { artwork ->
                    artwork.copy(imageUrl = getImageUrl(artwork.imageId, qualityType = High))
                }.toSet()
                emit(Result.success(GetArtworksByPageResult(artworksWithImages)))
            }.onFailure {
                emit(Result.failure(exception = it))
            }
        }
    }*/
}
