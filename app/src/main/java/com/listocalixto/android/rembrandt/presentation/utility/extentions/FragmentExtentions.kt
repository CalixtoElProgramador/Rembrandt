package com.listocalixto.android.rembrandt.presentation.utility

import android.graphics.Color
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.R.attr.colorError
import com.google.android.material.R.attr.colorErrorContainer
import com.google.android.material.R.attr.colorOnErrorContainer
import com.google.android.material.R.attr.colorOnSurfaceInverse
import com.google.android.material.R.attr.colorPrimaryInverse
import com.google.android.material.R.attr.colorSurfaceInverse
import com.google.android.material.color.MaterialColors
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackbar(
    view: View,
    uiText: UiText,
    duration: SnackbarDuration,
    anchorView: View? = null,
    @StringRes actionRes: Int? = null,
    isAnError: Boolean = false,
    onAction: ((snackbar: Snackbar) -> Unit)? = null,
) {
    val context = context ?: return
    val resources = context.resources ?: return
    val message = when (uiText) {
        is UiText.StringResource -> resources.getString(uiText.value)
        is UiText.StringValue -> uiText.value
    }
    val containerColor =
        if (isAnError) getColor(colorErrorContainer) else getColor(colorSurfaceInverse)
    val textColor =
        if (isAnError) getColor(colorOnErrorContainer) else getColor(colorOnSurfaceInverse)
    val actionColor = if (isAnError) getColor(colorError) else getColor(colorPrimaryInverse)

    Snackbar.make(view, message, duration.value).apply {
        setBackgroundTint(containerColor)
        setTextColor(textColor)
        setActionTextColor(actionColor)
        this.anchorView = anchorView
        actionRes?.let {
            this.setAction(actionRes) {
                onAction?.invoke(this)
            }
        }
        show()
    }
}

fun Fragment.getColor(@AttrRes colorAttr: Int): Int {
    val context = context ?: return Color.MAGENTA
    return MaterialColors.getColor(context, colorAttr, Color.MAGENTA)
}
