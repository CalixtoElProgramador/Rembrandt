package com.listocalixto.android.rembrandt.domain.utility

sealed class RecommendationType(open val id: String) {
    //    data class SameTechnique(override val id: String) : RecommendationType(id)
    //    data class SameStyle(override val id: String) : RecommendationType(id)
    //    data class SameSubject(override val id: String) : RecommendationType(id)
    data class SameGallery(override val id: String) : RecommendationType(id)
    data class SameArtist(override val id: String) : RecommendationType(id)
    data class SameCategory(override val id: String) : RecommendationType(id)
    data class SameArtworkType(override val id: String) : RecommendationType(id)
}
