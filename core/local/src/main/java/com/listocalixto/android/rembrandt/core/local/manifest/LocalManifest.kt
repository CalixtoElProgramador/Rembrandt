package com.listocalixto.android.rembrandt.core.local.manifest

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "manifests")
data class LocalManifest(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val description: String,
    val attribution: String,
    @ColumnInfo(name = "logo_attribution")
    val logoAttribution: String,
)
