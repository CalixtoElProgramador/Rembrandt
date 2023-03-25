package com.listocalixto.android.rembrandt.domain.usecase.main

import com.listocalixto.android.rembrandt.domain.entity.Artwork
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetArtworksAccordingSearchUseCase {

    operator fun invoke(query: String): Flow<Result<Artwork>> = flow {
    }

    companion object {
        fun validateQuery(query: String): Boolean {
            if (query.isBlank() || query.isEmpty()) {
                throw Exception()
            }
            return true
        }

        fun applyCorrectFormatInTheQuery(query: String): String {
            val querySplit = query.split(BLANK_SPACE).toMutableList()
            querySplit.removeIf { it.isEmpty() || it == "\\n" || it == "\\t" }
            var queryFormatted: String = BLANK_SPACE
            querySplit.forEach { word ->
                queryFormatted += "$word$BLANK_SPACE"
            }
            return queryFormatted.trim()
        }
        private const val BLANK_SPACE = " "
    }
}
