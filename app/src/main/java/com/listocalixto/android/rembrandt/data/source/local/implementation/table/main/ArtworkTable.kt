package com.listocalixto.android.rembrandt.data.source.local.implementation.table.main

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.listocalixto.android.rembrandt.data.source.local.configuration.ListConverter
import com.listocalixto.android.rembrandt.data.source.local.implementation.model.ColorLocal
import com.listocalixto.android.rembrandt.data.source.local.implementation.model.ManifestLocal
import com.listocalixto.android.rembrandt.data.source.local.implementation.model.ThumbnailLocal
import com.listocalixto.android.rembrandt.data.source.local.implementation.model.TranslationLocal

@Entity(tableName = "artworks")
data class ArtworkTable(
    val artistDisplay: String,
    val artistId: Int,
    val artistTitle: String,
    val artworkTypeId: Int,
    val artworkTypeTitle: String,
    @TypeConverters(value = [ListConverter::class])
    val categoryIds: List<String>,
    @TypeConverters(value = [ListConverter::class])
    val categoryTitles: List<String>,
    @Embedded
    val color: ColorLocal,
    val creditLine: String,
    val dateDisplay: String,
    val dateEnd: Int,
    val dateStart: Int,
    val dimensions: String,
    val galleryId: Int,
    val galleryTitle: String,
    val hasNotBeenViewedMuch: Boolean,
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val isFavorite: Boolean,
    val imageId: String,
    val latitude: Double,
    val longitude: Double,
    @Embedded
    val manifest: ManifestLocal?,
    val mediumDisplay: String,
    val placeOfOrigin: String,
    val score: Double,
    @TypeConverters(value = [ListConverter::class])
    val termTitles: List<String>,
    @Embedded
    val translation: TranslationLocal?,
    @Embedded
    val thumbnail: ThumbnailLocal,
    val title: String
)
