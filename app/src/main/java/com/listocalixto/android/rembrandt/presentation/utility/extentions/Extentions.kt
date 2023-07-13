package com.listocalixto.android.rembrandt.presentation.utility.extentions

import android.graphics.Bitmap
import androidx.palette.graphics.Palette

fun Bitmap.palette(): Palette {
    return Palette.from(this).generate()
}
