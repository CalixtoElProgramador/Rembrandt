package com.listocalixto.android.rembrandt.presentation.utility

import androidx.annotation.AttrRes

sealed interface ColorContainerType {
    data class AllContainerColors(@AttrRes val colorAttr: Int) : ColorContainerType
    data class DifferentContainerColors(
        @AttrRes val containerColor: Int,
        @AttrRes val startContainerColor: Int,
        @AttrRes val endContainerColor: Int
    ) : ColorContainerType
}
