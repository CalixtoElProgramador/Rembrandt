package com.listocalixto.android.rembrandt.core.ui.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.os.Build
import android.view.View
import android.view.View.ALPHA
import android.widget.ImageView
import androidx.annotation.AttrRes
import androidx.annotation.IntegerRes
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.graphics.drawable.toBitmap
import androidx.core.widget.NestedScrollView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.resources.MaterialAttributes
import com.listocalixto.android.rembrandt.common.designsystem.R as RDS

@SuppressLint("RestrictedApi")
fun View.fader(
    emphasisType: EmphasisType,
    viewTrigger: View? = null,
    @IntegerRes durationRes: Int = RDS.integer.motion_duration_small,
) {
    MaterialAttributes.resolve(context, emphasisType.valueAttrRes)?.float?.let { targetAlpha ->
        val duration = resources.getInteger(durationRes).toLong()
        val set = AnimatorSet().apply { this.duration = duration }
        val fromCurrentAlphaToZero = ObjectAnimator.ofFloat(this, ALPHA, this.alpha, 0f)
        val fromZeroToTargetAlpha = ObjectAnimator.ofFloat(this, ALPHA, 0f, targetAlpha)
        set.playSequentially(fromCurrentAlphaToZero, fromZeroToTargetAlpha)
        set.start()

        /*ObjectAnimator.ofFloat(
            this,
            ALPHA,
            0f,
        ).run {
            this.duration = resources.getInteger(durationRes).toLong()
            repeatCount = 1
            repeatMode = ObjectAnimator.REVERSE
            viewTrigger?.let { disableClickDuringAnimation(it) }
            start()
        }*/
    }
}

@SuppressLint("RestrictedApi")
fun View.emphasizes(
    emphasisType: EmphasisType,
    @IntegerRes durationRes: Int = RDS.integer.motion_duration_small,
) {
    MaterialAttributes.resolve(context, emphasisType.valueAttrRes)?.float?.let {
        ObjectAnimator.ofFloat(
            this,
            ALPHA,
            this.alpha,
            it,
        ).run {
            duration = resources.getInteger(durationRes).toLong()
            start()
        }
    }
}

enum class EmphasisType(@AttrRes val valueAttrRes: Int) {
    Disable(RDS.attr.emphasisDisabledAlpha), Medium(RDS.attr.emphasisMediumAlpha), High(RDS.attr.emphasisHighAlpha)
}

fun View.colorize(
    currentColor: Int,
    newColor: Int,
    @IntegerRes durationRes: Int = RDS.integer.motion_duration_large,
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

private fun ObjectAnimator.onAnimation(start: (() -> Unit)?, end: (() -> Unit)?) {
    addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator) {
            super.onAnimationStart(animation)
            start?.invoke()
        }

        override fun onAnimationEnd(animation: Animator) {
            super.onAnimationEnd(animation)
            end?.invoke()
        }
    })
}

fun ExtendedFloatingActionButton.adjustSizeAccordingScroll(scrollView: NestedScrollView) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        scrollView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY - oldScrollY < 0) {
                extend()
            }
            if (scrollY - oldScrollY > 0) {
                shrink()
            }
        }
    }
}

fun View.visible() = apply { this.visibility = View.VISIBLE }

fun View.invisible() = apply { this.visibility = View.INVISIBLE }

fun View.gone() = apply { this.visibility = View.GONE }

fun ImageView.clipboardImage() {
    drawable?.toBitmap()?.let {
        val uri = context.getImageUri(it)
        val clipData = ClipData.newUri(context.contentResolver, "Image", uri)
        getSystemService(context, ClipboardManager::class.java)?.run { setPrimaryClip(clipData) }
    }
}
