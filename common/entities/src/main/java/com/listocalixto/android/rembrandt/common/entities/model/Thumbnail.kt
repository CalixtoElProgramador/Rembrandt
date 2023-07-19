package com.listocalixto.android.rembrandt.common.entities.model

data class Thumbnail(
    val altText: String,
    val height: Int?,
    val lqip: String,
    val width: Int?
) {

    companion object {
        val defaultInstance = Thumbnail(altText = "", height = -1, lqip = "", width = -1)
    }
}
