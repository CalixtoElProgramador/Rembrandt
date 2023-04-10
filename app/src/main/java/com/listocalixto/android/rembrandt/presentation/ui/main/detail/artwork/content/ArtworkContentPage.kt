package com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.content

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

interface ArtworkContentPage {
    @get:StringRes
    val tabTitleRes: Int
    val instance: Fragment
}
