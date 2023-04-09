package com.listocalixto.android.rembrandt.data.source.local.implementation.table.main

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.listocalixto.android.rembrandt.data.source.local.configuration.ListConverter
import com.listocalixto.android.rembrandt.data.source.local.implementation.model.MetadataLocal

@Entity(tableName = "manifests")
data class ManifestTable(
    val artworkId: Long,
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val description: String,
    @TypeConverters(value = [ListConverter::class])
    val metadata: List<MetadataLocal>
)
