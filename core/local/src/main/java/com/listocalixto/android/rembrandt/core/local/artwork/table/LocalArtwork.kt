package com.listocalixto.android.rembrandt.core.local.artwork.table

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.listocalixto.android.rembrandt.core.local.artwork.dto.LocalColor
import com.listocalixto.android.rembrandt.core.local.artwork.dto.LocalThumbnail
import com.listocalixto.android.rembrandt.core.local.utility.ListConverter

@Entity(tableName = "artworks")
data class LocalArtwork(
    val artistDisplay: String,
    val artistId: Int?,
    val artistTitle: String?,
    val artworkTypeId: Int,
    val artworkTypeTitle: String,
    @TypeConverters(value = [ListConverter::class])
    val categoryIds: List<String>,
    @TypeConverters(value = [ListConverter::class])
    val categoryTitles: List<String>,
    @Embedded
    val color: LocalColor?,
    val creditLine: String,
    val dateDisplay: String?,
    val dateEnd: Int?,
    val dateStart: Int?,
    val dimensions: String?,
    val galleryId: Int?,
    val galleryTitle: String?,
    val hasNotBeenViewedMuch: Boolean,
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val inscriptions: String?,
    val imageId: String,
    val imageUrl: String,
    val latitude: Double?,
    val longitude: Double?,
    val mediumDisplay: String?,
    val placeOfOrigin: String?,
    val score: Double,
    val styleId: String?,
    val styleTitle: String?,
    @TypeConverters(value = [ListConverter::class])
    val termTitles: List<String>,
    @Embedded
    val thumbnail: LocalThumbnail,
    val title: String,
)
