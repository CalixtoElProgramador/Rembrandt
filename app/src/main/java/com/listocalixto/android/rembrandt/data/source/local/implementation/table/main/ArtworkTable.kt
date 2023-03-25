package com.listocalixto.android.rembrandt.data.source.local.implementation.table.main

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artwork")
data class ArtworkTable(
    @PrimaryKey
    val id: Long
)
