package com.listocalixto.android.rembrandt.core.local.utility

import androidx.room.TypeConverter
import com.listocalixto.android.rembrandt.core.local.artwork.dto.MetadataLocal
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ListConverter {

    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return try {
            Json.decodeFromString(string = value)
        } catch (e: Exception) {
            emptyList()
        }
    }

    @TypeConverter
    fun fromMetadataList(value: List<MetadataLocal>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toMetadataList(value: String): List<MetadataLocal> {
        return try {
            Json.decodeFromString(value)
        } catch (e: Exception) {
            emptyList()
        }
    }
}
