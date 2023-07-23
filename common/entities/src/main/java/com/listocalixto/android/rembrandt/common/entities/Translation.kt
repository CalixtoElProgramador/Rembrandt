package com.listocalixto.android.rembrandt.common.entities

typealias key = String
typealias translation = String

data class Translation(
    val id: String,
    val keysAndTranslations: Map<key, translation>,
) {
    object TargetLanguageNotAvailableException : Exception(
        "The language requested is not available to translate.",
    )
}
