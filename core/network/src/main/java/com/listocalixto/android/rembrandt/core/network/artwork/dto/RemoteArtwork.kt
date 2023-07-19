package com.listocalixto.android.rembrandt.core.network.artwork.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteArtwork(
    @SerialName("artist_display")
    val artistDisplay: String,
    @SerialName("artist_id")
    val artistId: Int?,
    @SerialName("artist_title")
    val artistTitle: String?,
    @SerialName("artwork_type_id")
    val artworkTypeId: Int,
    @SerialName("artwork_type_title")
    val artworkTypeTitle: String,
    @SerialName("category_ids")
    val categoryIds: List<String>,
    @SerialName("category_titles")
    val categoryTitles: List<String>,
    @SerialName("color")
    val color: RemoteColor?,
    @SerialName("credit_line")
    val creditLine: String,
    @SerialName("date_display")
    val dateDisplay: String?,
    @SerialName("date_end")
    val dateEnd: Int?,
    @SerialName("date_start")
    val dateStart: Int?,
    @SerialName("dimensions")
    val dimensions: String?,
    @SerialName("gallery_id")
    val galleryId: Int?,
    @SerialName("gallery_title")
    val galleryTitle: String?,
    @SerialName("has_not_been_viewed_much")
    val hasNotBeenViewedMuch: Boolean,
    @SerialName("id")
    val id: Long,
    @SerialName("image_id")
    val imageId: String,
    @SerialName("latitude")
    val latitude: Double?,
    @SerialName("longitude")
    val longitude: Double?,
    @SerialName("medium_display")
    val mediumDisplay: String?,
    @SerialName("place_of_origin")
    val placeOfOrigin: String?,
    @SerialName("_score")
    val score: Double,
    @SerialName("term_titles")
    val termTitles: List<String>,
    @SerialName("thumbnail")
    val thumbnail: RemoteThumbnail,
    @SerialName("title")
    val title: String
)
