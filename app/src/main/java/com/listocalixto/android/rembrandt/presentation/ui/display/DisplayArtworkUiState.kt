package com.listocalixto.android.rembrandt.presentation.ui.display

import com.listocalixto.android.rembrandt.presentation.utility.UiText

data class DisplayArtworkUiState(
    val imageUrl: String = "",
    val altText: String? = null,
    val memoryCacheKey: String? = null,
    val errorMessage: UiText? = null,
    val focusX: Float = 0.5f,
    val focusY: Float = 0.5f,
    val scale: Float = 1.0f,
    val shouldAnimateZoom: Boolean = true,
)
