package com.listocalixto.android.rembrandt.common.entities

data class Translation(
    val category: String,
    val title: String,
    val content: String,
) {

    sealed class Exception(override val message: String?) : kotlin.Exception(message) {
        object TargetLanguageNotAvailable : Exception(
            "The language requested is not available to translate.",
        )
    }
}
