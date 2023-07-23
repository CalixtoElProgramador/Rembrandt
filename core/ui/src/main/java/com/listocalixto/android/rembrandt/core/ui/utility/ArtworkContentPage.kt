package com.listocalixto.android.rembrandt.core.ui.utility

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

interface ArtworkContentPage {
    @get:StringRes
    val tabTitleRes: Int
    val instance: Fragment
}
