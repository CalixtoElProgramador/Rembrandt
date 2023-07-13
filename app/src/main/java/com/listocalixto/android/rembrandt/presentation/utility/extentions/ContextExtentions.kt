package com.listocalixto.android.rembrandt.presentation.utility.extentions

import android.content.Context
import android.content.res.Configuration

fun Context.isDarkMode(): Boolean {
    val nightModeFlags = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    return nightModeFlags == Configuration.UI_MODE_NIGHT_YES
}
