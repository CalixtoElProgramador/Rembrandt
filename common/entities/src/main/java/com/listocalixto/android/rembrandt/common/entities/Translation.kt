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

    companion object {
        const val CATEGORY_TRANSLATION_KEY = "category"
        const val DESCRIPTION_TRANSLATION_KEY = "description"
        const val TITLE_TRANSLATION_KEY = "title"
        const val MEDIUM_DISPLAY_TRANSLATION_KEY = "medium_display"
    }
}
