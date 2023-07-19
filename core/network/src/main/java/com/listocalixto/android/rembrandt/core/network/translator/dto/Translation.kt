package com.listocalixto.android.rembrandt.core.network.translator.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Translation(
    @SerialName("detected_source_language")
    val detectedSourceLanguage: String,
    @SerialName("text")
    val text: String
)
