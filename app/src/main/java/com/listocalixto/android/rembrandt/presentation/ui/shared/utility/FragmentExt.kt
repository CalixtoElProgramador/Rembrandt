package com.listocalixto.android.rembrandt.presentation.ui.shared.utility

import android.graphics.Color
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.IdRes
import androidx.annotation.IntegerRes
import androidx.fragment.app.Fragment
import com.google.android.material.color.MaterialColors
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialFadeThrough
import com.listocalixto.android.rembrandt.R
import com.listocalixto.android.rembrandt.presentation.ui.shared.utility.ColorContainerType.AllContainerColors
import com.listocalixto.android.rembrandt.presentation.ui.shared.utility.ColorContainerType.DifferentContainerColors

fun Fragment.applyFadeThroughEnterTransition(
    @IntegerRes durationRes: Int = R.integer.motion_duration_large,
) = run {
    enterTransition = MaterialFadeThrough().apply {
        duration = resources.getInteger(durationRes).toLong()
    }
}

fun Fragment.applyFadeThroughExitTransition(
    @IntegerRes durationRes: Int = R.integer.motion_duration_large,
) = run {
    exitTransition = MaterialFadeThrough().apply {
        duration = resources.getInteger(durationRes).toLong()
    }
}

fun Fragment.applySharedElementEnterTransition(
    @IdRes drawingViewIdRes: Int,
    @IntegerRes durationRes: Int = R.integer.motion_duration_large,
    @ColorInt scrimColorInt: Int = Color.TRANSPARENT,
    colorContainerType: ColorContainerType,
) = run {
    val context = context ?: return@run
    sharedElementEnterTransition = MaterialContainerTransform().apply {
        drawingViewId = drawingViewIdRes
        duration = resources.getInteger(durationRes).toLong()
        scrimColor = scrimColorInt
        when (colorContainerType) {
            is AllContainerColors -> {
                setAllContainerColors(
                    MaterialColors.getColor(
                        context,
                        colorContainerType.colorAttr,
                        Color.MAGENTA,
                    ),
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
    @IntegerRes durationRes: Int = R.integer.motion_duration_large,
) = run {
    exitTransition = MaterialElevationScale(false).apply {
        duration = resources.getInteger(durationRes).toLong()
    }
    reenterTransition = MaterialElevationScale(true).apply {
        duration = resources.getInteger(durationRes).toLong()
    }
}

sealed interface ColorContainerType {
    data class AllContainerColors(@AttrRes val colorAttr: Int) : ColorContainerType
    data class DifferentContainerColors(
        @AttrRes val containerColor: Int,
        @AttrRes val startContainerColor: Int,
        @AttrRes val endContainerColor: Int,
    ) : ColorContainerType
}
