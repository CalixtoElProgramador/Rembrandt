package com.listocalixto.android.rembrandt.core.ui.utility

import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

abstract class ArtworkContentPage(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {
    @get:StringRes
    abstract val tabTitleRes: Int
    abstract val instance: Fragment
}
