package com.listocalixto.android.rembrandt.core.local.utility

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object SetConverter {
    @TypeConverter
    fun setLongToString(value: Set<Long>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun stringToSetLong(value: String): Set<Long> {
        return try {
            Json.decodeFromString(string = value)
        } catch (e: Exception) {
            emptySet()
        }
    }
}
