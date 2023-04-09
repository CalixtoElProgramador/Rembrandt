package com.listocalixto.android.rembrandt.data.source.remote.implementation.response.shared

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TranslationResponse(
    @SerialName("translations")
    val translations: List<Translation>
)
