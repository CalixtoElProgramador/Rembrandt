package com.listocalixto.android.rembrandt.core.network.translator.response

import com.listocalixto.android.rembrandt.core.network.translator.dto.Translation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TranslationResponse(
    @SerialName("translations")
    val translations: List<Translation>,
)
