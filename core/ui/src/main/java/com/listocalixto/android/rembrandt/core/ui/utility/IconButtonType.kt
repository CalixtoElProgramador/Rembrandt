package com.listocalixto.android.rembrandt.core.ui.utility

import android.R.color.transparent
import android.content.Context
import androidx.annotation.AttrRes
import com.google.android.material.R.attr.colorOnPrimary
import com.google.android.material.R.attr.colorOnSecondaryContainer
import com.google.android.material.R.attr.colorOnSurfaceVariant
import com.google.android.material.R.attr.colorPrimary
import com.google.android.material.R.attr.colorSecondaryContainer
import com.google.android.material.R.attr.colorSurfaceVariant
import com.listocalixto.android.rembrandt.core.ui.extensions.getAttrColor

enum class IconButtonType(
    @AttrRes private val backgroundColorSelected: Int?,
    @AttrRes private val iconTintSelected: Int,
    @AttrRes private val backgroundColorUnselected: Int?,
    @AttrRes private val iconTintUnselected: Int,
) {
    Filled(
        backgroundColorSelected = colorPrimary,
        iconTintSelected = colorOnPrimary,
        backgroundColorUnselected = colorSurfaceVariant,
        iconTintUnselected = colorPrimary,
    ),
    FilledTonal(
        backgroundColorSelected = colorSecondaryContainer,
        iconTintSelected = colorOnSecondaryContainer,
        backgroundColorUnselected = colorSurfaceVariant,
        iconTintUnselected = colorOnSurfaceVariant,
    ),
    Standard(
        backgroundColorSelected = null,
        iconTintSelected = colorPrimary,
        backgroundColorUnselected = null,
        iconTintUnselected = colorOnSurfaceVariant,
    ),
    ;

    fun getBackgroundColor(context: Context?, toggle: Boolean): Int {
        if (backgroundColorSelected == null || backgroundColorUnselected == null) return transparent
        return if (toggle) {
            context.getAttrColor(backgroundColorSelected)
        } else {
            context.getAttrColor(backgroundColorUnselected)
        }
    }

    fun getIconTint(context: Context?, toggle: Boolean): Int {
        return if (toggle) {
            context.getAttrColor(iconTintSelected)
        } else {
            context.getAttrColor(iconTintUnselected)
        }
    }
}
