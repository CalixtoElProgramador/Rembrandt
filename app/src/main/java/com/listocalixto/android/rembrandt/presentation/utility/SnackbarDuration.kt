package com.listocalixto.android.rembrandt.presentation.utility

import com.google.android.material.snackbar.Snackbar

enum class SnackbarDuration(val value: Int) {
    SHORT(value = Snackbar.LENGTH_SHORT),
    LONG(value = Snackbar.LENGTH_LONG),
    INDEFINITE(value = Snackbar.LENGTH_INDEFINITE)
}
