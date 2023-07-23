package com.listocalixto.android.rembrandt.core.local.translator

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.listocalixto.android.rembrandt.core.local.utility.MapConverter

@Entity(tableName = "translations")
data class LocalTranslation(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @ColumnInfo(name = "keys_and_translations")
    @TypeConverters(value = [MapConverter::class])
    val keysAndTranslations: Map<String, String>,
)
