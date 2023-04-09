package com.listocalixto.android.rembrandt.presentation.utility

import androidx.annotation.StringRes

sealed interface UiText {
    data class StringResource(@StringRes val value: Int) : UiText
    data class StringValue(val value: String) : UiText
}
