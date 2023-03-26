package com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork

import androidx.annotation.StringRes

data class ArtworkRecommendedUiState(
    val id: Long = -1,
    val imageUrl: String = "",
    val title: String = "",
    @StringRes val reasonItWasRecommended: Int = -1,
)
