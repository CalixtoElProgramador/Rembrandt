package com.listocalixto.android.rembrandt.core.ui.extensions

import android.graphics.Bitmap
import androidx.palette.graphics.Palette

fun Bitmap.palette(): Palette {
    return Palette.from(this).generate()
}
