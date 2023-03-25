package com.listocalixto.android.rembrandt.presentation.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.listocalixto.android.rembrandt.R

@BindingAdapter("loadImage")
fun ImageView.bindingLoadImage(imageUrl: String) {
    load(imageUrl) {
        crossfade(true)
        placeholder(R.drawable.ic_explore)
    }
}
