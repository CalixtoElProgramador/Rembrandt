package com.listocalixto.android.rembrandt.core.network.translator

import kotlinx.serialization.json.Json

object Utils {
    val myJson = Json {
        encodeDefaults = false
        ignoreUnknownKeys = true
        isLenient = true
        allowSpecialFloatingPointValues = true
        coerceInputValues = true
        prettyPrint = true
        explicitNulls = false
    }
}
