package com.listocalixto.android.rembrandt.feature.artworkdetail.content

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

interface ArtworkContentPage {
    @get:StringRes
    val tabTitleRes: Int
    val instance: Fragment
}
