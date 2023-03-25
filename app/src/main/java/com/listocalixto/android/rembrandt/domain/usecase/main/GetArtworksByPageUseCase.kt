package com.listocalixto.android.rembrandt.domain.usecase.main

import com.listocalixto.android.rembrandt.domain.repo.ArtworkRepo
import com.listocalixto.android.rembrandt.domain.result.GetArtworksByPageResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetArtworksByPageUseCase @Inject constructor(
    private val repo: ArtworkRepo,
) {
    operator fun invoke(page: Int): Flow<Result<GetArtworksByPageResult>> = flow {
        val pageString = page.toString()
        repo.getArtworksByPage(pageString).collect { result ->
            result.onSuccess { artworks ->
                emit(Result.success(GetArtworksByPageResult(artworks)))
            }.onFailure {
                emit(Result.failure(exception = it))
            }
        }
    }
}
