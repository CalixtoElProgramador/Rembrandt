package com.listocalixto.android.rembrandt.core.ui.states

import androidx.annotation.StringRes
import com.listocalixto.android.rembrandt.core.ui.utility.UiText

data class ArtworkCharacteristicUiState(
    @StringRes val label: Int,
    val value: UiText,
)
