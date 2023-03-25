package com.listocalixto.android.rembrandt.domain.entity

import com.listocalixto.android.rembrandt.domain.model.Color
import com.listocalixto.android.rembrandt.domain.model.Thumbnail

data class Artwork(
    val artistDisplay: String,
    val artistId: Int,
    val artistTitle: String,
    val artworkTypeId: Int,
    val artworkTypeTitle: String,
    val categoryIds: List<String>,
    val categoryTitles: List<String>,
    val color: Color,
    val creditLine: String,
    val dateDisplay: String,
    val dateEnd: Int,
    val dateStart: Int,
    val dimensions: String,
    val galleryId: Int,
    val galleryTitle: String,
    val hasNotBeenViewedMuch: Boolean,
    val id: Long,
    val imageId: String,
    val latitude: Double,
    val longitude: Double,
    val mediumDisplay: String,
    val placeOfOrigin: String,
    val score: Double,
    val termTitles: List<String>,
    val thumbnail: Thumbnail,
    val title: String,
)
