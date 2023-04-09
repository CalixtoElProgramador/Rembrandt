package com.listocalixto.android.rembrandt.presentation.utility

import android.graphics.Color
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.IdRes
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.google.android.material.R.attr.colorError
import com.google.android.material.R.attr.colorErrorContainer
import com.google.android.material.R.attr.colorOnErrorContainer
import com.google.android.material.R.attr.colorOnSurfaceInverse
import com.google.android.material.R.attr.colorPrimaryInverse
import com.google.android.material.R.attr.colorSurfaceInverse
import com.google.android.material.color.MaterialColors
import com.google.android.material.motion.MotionUtils
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialFadeThrough
import com.listocalixto.android.rembrandt.R
import com.listocalixto.android.rembrandt.presentation.utility.ColorContainerType.AllContainerColors
import com.listocalixto.android.rembrandt.presentation.utility.ColorContainerType.DifferentContainerColors

fun Fragment.showSnackbar(
    view: View,
    uiText: UiText,
    duration: SnackbarDuration,
    anchorView: View? = null,
    @StringRes actionRes: Int? = null,
    isAnError: Boolean = false,
    onAction: ((snackbar: Snackbar) -> Unit)? = null
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

fun Fragment.applyFadeThroughEnterTransition(
    @IntegerRes durationRes: Int = R.integer.motion_duration_large
) = run {
    enterTransition = MaterialFadeThrough().apply {
        duration = resources.getInteger(durationRes).toLong()
    }
}

fun Fragment.applyFadeThroughExitTransition(
    @IntegerRes durationRes: Int = R.integer.motion_duration_large
) = run {
    exitTransition = MaterialFadeThrough().apply {
        duration = resources.getInteger(durationRes).toLong()
    }
}

fun Fragment.applySharedElementEnterTransition(
    @IdRes drawingViewIdRes: Int,
    @IntegerRes durationRes: Int = R.integer.motion_duration_large,
    @ColorInt scrimColorInt: Int = Color.TRANSPARENT,
    colorContainerType: ColorContainerType
) = run {
    val context = context ?: return@run
    sharedElementEnterTransition = MaterialContainerTransform().apply {
        drawingViewId = drawingViewIdRes
        duration = resources.getInteger(durationRes).toLong()
        scrimColor = scrimColorInt
        interpolator = MotionUtils.resolveThemeInterpolator(
            context,
            com.google.android.material.R.attr.motionEasingEmphasizedInterpolator,
            FastOutSlowInInterpolator()
        )
        fadeMode = MaterialContainerTransform.FADE_MODE_IN
        when (colorContainerType) {
            is AllContainerColors -> {
                setAllContainerColors(
                    MaterialColors.getColor(
                        context,
                        colorContainerType.colorAttr,
                        Color.MAGENTA
                    )
                )
            }
            is DifferentContainerColors -> {
                this.containerColor = colorContainerType.containerColor
                this.startContainerColor = colorContainerType.startContainerColor
                this.endContainerColor = colorContainerType.endContainerColor
            }
        }
    }
}

fun Fragment.applySharedElementExitTransition(
    @IntegerRes durationRes: Int = R.integer.motion_duration_large
) = run {
    val context = context ?: return@run
    exitTransition = MaterialElevationScale(false).apply {
        duration = resources.getInteger(durationRes).toLong()
        interpolator = MotionUtils.resolveThemeInterpolator(
            context,
            com.google.android.material.R.attr.motionEasingEmphasizedInterpolator,
            FastOutSlowInInterpolator()
        )
    }
    reenterTransition = MaterialElevationScale(true).apply {
        duration = resources.getInteger(durationRes).toLong()
        interpolator = MotionUtils.resolveThemeInterpolator(
            context,
            com.google.android.material.R.attr.motionEasingEmphasizedInterpolator,
            FastOutSlowInInterpolator()
        )
    }
}

fun Fragment.getColor(@AttrRes colorAttr: Int): Int {
    val context = context ?: return Color.MAGENTA
    return MaterialColors.getColor(context, colorAttr, Color.MAGENTA)
}
