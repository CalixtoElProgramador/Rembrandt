package com.listocalixto.android.rembrandt.core.network.translator.response

import com.listocalixto.android.rembrandt.core.network.translator.dto.RemoteTranslation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TranslationResponse(
    @SerialName("translations")
    val translations: List<RemoteTranslation>,
)
