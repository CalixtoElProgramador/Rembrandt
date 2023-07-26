package com.listocalixto.android.rembrandt.feature.displayimage

import com.listocalixto.android.rembrandt.core.ui.utility.UiText

data class DisplayImageUiState(
    val imageUrl: String = "",
    val altText: String? = null,
    val memoryCacheKey: String? = null,
    val errorMessage: UiText? = null,
    val focusX: Float = 0.5f,
    val focusY: Float = 0.5f,
    val scale: Float = 1.0f,
    val shouldAnimateZoom: Boolean = true,
)
