package com.listocalixto.android.rembrandt.core.ui.states

import androidx.annotation.StringRes
import com.listocalixto.android.rembrandt.common.entities.utility.Constants.EMPTY
import com.listocalixto.android.rembrandt.common.entities.utility.Constants.INT_UNASSIGNED
import com.listocalixto.android.rembrandt.common.entities.utility.Constants.LONG_UNASSIGNED

data class ArtworkRecommendedUiState(
    val id: Long = LONG_UNASSIGNED,
    val imageUrl: String = EMPTY,
    val title: String = EMPTY,
    @StringRes val reasonItWasRecommended: Int = INT_UNASSIGNED,
)
