package com.listocalixto.android.rembrandt.core.local.utility

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MapConverter {
    @TypeConverter
    fun stringToMap(value: String): Map<String, String> {
        return try {
            Json.decodeFromString(string = value)
        } catch (e: Exception) {
            emptyMap()
        }
    }

    @TypeConverter
    fun mapToString(value: Map<String, String>): String {
        return Json.encodeToString(value)
    }
}
