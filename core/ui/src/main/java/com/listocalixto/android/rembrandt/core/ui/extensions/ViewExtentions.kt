package com.listocalixto.android.rembrandt.presentation.utility.extentions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Build
import android.view.View
import androidx.annotation.IntegerRes
import androidx.annotation.RequiresApi
import androidx.core.widget.NestedScrollView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.listocalixto.android.rembrandt.common.entities.utility.Constants.ANIMATION_REFRESH_DURATION
import com.listocalixto.android.rembrandt.core.ui.R

fun View.fader(viewTrigger: View? = null) {
    ObjectAnimator.ofFloat(this, View.ALPHA, 0f).run {
        duration = ANIMATION_REFRESH_DURATION
        repeatCount = 1
        repeatMode = ObjectAnimator.REVERSE
        viewTrigger?.let { disableClickDuringAnimation(it) }
        start()
    }
}

fun View.colorize(
    currentColor: Int,
    newColor: Int,
    @IntegerRes durationRes: Int = R.integer.motion_duration_large,
) {
    ObjectAnimator.ofArgb(
        this,
        "backgroundColor",
        currentColor,
        newColor,
    ).run {
        duration = resources.getInteger(durationRes).toLong()
        start()
    }
}

private fun ObjectAnimator.disableClickDuringAnimation(view: View) {
    addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator) {
            super.onAnimationStart(animation)
            view.isClickable = false
        }

        override fun onAnimationEnd(animation: Animator) {
            super.onAnimationEnd(animation)
            view.isClickable = true
        }
    })
}

@RequiresApi(Build.VERSION_CODES.M)
fun ExtendedFloatingActionButton.adjustSizeAccordingScroll(scrollView: NestedScrollView) {
    scrollView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
        if (scrollY - oldScrollY < 0) {
            extend()
        }
        if (scrollY - oldScrollY > 0) {
            shrink()
        }
    }
}

fun View.visible() = apply { this.visibility = View.VISIBLE }

fun View.invisible() = apply { this.visibility = View.INVISIBLE }

fun View.gone() = apply { this.visibility = View.GONE }