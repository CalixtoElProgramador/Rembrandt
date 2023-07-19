package com.listocalixto.android.rembrandt.core.local.artwork.dto

import androidx.room.ColumnInfo
import androidx.room.TypeConverters
import com.listocalixto.android.rembrandt.core.local.utility.ListConverter

data class ManifestLocal(
    val artworkId: Long,
    @ColumnInfo(name = "manifest_id")
    val id: String,
    @ColumnInfo(name = "manifest_description")
    val description: String,
    @TypeConverters(value = [ListConverter::class])
    val metadata: List<MetadataLocal>,
)
