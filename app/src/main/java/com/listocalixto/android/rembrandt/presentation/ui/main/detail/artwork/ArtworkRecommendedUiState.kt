package com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork

import androidx.annotation.StringRes
import com.listocalixto.android.rembrandt.core.Constants.EMPTY
import com.listocalixto.android.rembrandt.core.Constants.INT_UNASSIGNED
import com.listocalixto.android.rembrandt.core.Constants.LONG_UNASSIGNED

data class ArtworkRecommendedUiState(
    val id: Long = LONG_UNASSIGNED,
    val imageUrl: String = EMPTY,
    val title: String = EMPTY,
    @StringRes val reasonItWasRecommended: Int = INT_UNASSIGNED
)
