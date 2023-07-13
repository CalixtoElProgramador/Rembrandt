package com.listocalixto.android.rembrandt.presentation.view.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.databinding.BindingAdapter
import coil.load
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.google.android.material.color.MaterialColors
import com.listocalixto.android.rembrandt.presentation.utility.UiText

@BindingAdapter("uiText")
fun TextView.bindUiText(uiText: UiText?) = uiText?.let {
    text = when (uiText) {
        is UiText.StringResource -> {
            context.resources.getText(uiText.value)
        }

        is UiText.StringValue -> {
            uiText.value
        }
    }
}

@BindingAdapter("loadImage")
fun ImageView.bindLoadImage(imageUrl: String?) {
    imageUrl?.let { url ->
        load(url) {
            allowHardware(false)
            memoryCacheKey(url)
            crossfade(true)
        }
    }
}

@BindingAdapter("loadFromCache", "memoryCacheKey", requireAll = true)
fun ImageView.bindLoadFromCache(imageUrl: String?, memoryCacheKey: String?) {
    memoryCacheKey?.let { key ->
        imageUrl?.let { url ->
            load(url) {
                allowHardware(false)
                placeholderMemoryCacheKey(key)
                crossfade(true)
            }
        }
    } ?: run {
        bindLoadImage(imageUrl)
    }
}

@BindingAdapter("tint")
fun ImageView.bindTint(@AttrRes attr: Int) {
    imageTintList =
        MaterialColors.getColorStateList(context, attr, ColorStateList.valueOf(Color.MAGENTA))
}

@BindingAdapter("srcCompat")
fun ImageView.bindSrc(drawable: Drawable) {
    setImageDrawable(drawable)
}
