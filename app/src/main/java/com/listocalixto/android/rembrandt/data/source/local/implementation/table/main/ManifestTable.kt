package com.listocalixto.android.rembrandt.data.source.local.implementation.table.main

import androidx.room.ColumnInfo
import androidx.room.TypeConverters
import com.listocalixto.android.rembrandt.data.source.local.configuration.ListConverter
import com.listocalixto.android.rembrandt.data.source.local.implementation.model.MetadataLocal

data class ManifestTable(
    val artworkId: Long,
    @ColumnInfo(name = "manifest_id")
    val id: String,
    @ColumnInfo(name = "manifest_description")
    val description: String,
    @TypeConverters(value = [ListConverter::class])
    val metadata: List<MetadataLocal>
)
