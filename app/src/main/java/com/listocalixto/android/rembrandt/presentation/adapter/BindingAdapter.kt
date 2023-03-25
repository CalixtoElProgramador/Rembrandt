package com.listocalixto.android.rembrandt.presentation.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.AttrRes
import androidx.databinding.BindingAdapter
import coil.load
import com.google.android.material.color.MaterialColors
import com.listocalixto.android.rembrandt.R

@BindingAdapter("loadImage")
fun ImageView.bindingLoadImage(imageUrl: String) {
    load(imageUrl) {
        crossfade(true)
        placeholder(R.drawable.ic_explore)
    }
}

@BindingAdapter("tint")
fun ImageView.bindingTint(@AttrRes attr: Int) {
    imageTintList =
        MaterialColors.getColorStateList(context, attr, ColorStateList.valueOf(Color.MAGENTA))
}

@BindingAdapter("srcCompat")
fun ImageView.bindingSrc(drawable: Drawable) {
    setImageDrawable(drawable)
}
