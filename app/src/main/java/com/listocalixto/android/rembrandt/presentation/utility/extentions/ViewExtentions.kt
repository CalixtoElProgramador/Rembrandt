package com.listocalixto.android.rembrandt.presentation.utility.extentions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.view.View
import androidx.core.widget.NestedScrollView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.listocalixto.android.rembrandt.core.Constants.ANIMATION_REFRESH_DURATION

fun View.fader(viewTrigger: View? = null) {
    ObjectAnimator.ofFloat(this, View.ALPHA, 0f).run {
        duration = ANIMATION_REFRESH_DURATION
        repeatCount = 1
        repeatMode = ObjectAnimator.REVERSE
        viewTrigger?.let { disableClickDuringAnimation(it, this) }
        start()
    }
}

private fun disableClickDuringAnimation(view: View, animator: ObjectAnimator) {
    animator.addListener(object : AnimatorListenerAdapter() {
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
