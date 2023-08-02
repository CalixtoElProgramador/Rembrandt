package com.listocalixto.android.rembrandt.core.ui.extensions

import android.content.Context
import android.content.res.Configuration
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import java.io.ByteArrayOutputStream

fun Context.isDarkMode(): Boolean {
    val nightModeFlags = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    return nightModeFlags == Configuration.UI_MODE_NIGHT_YES
}

fun Context.getImageUri(bitmap: Bitmap): Uri {
    val resolver = contentResolver
    val bytes = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
    val title = System.currentTimeMillis().toString()
    val path = MediaStore.Images.Media.insertImage(resolver, bitmap, title, null)
    return Uri.parse(path)
}
