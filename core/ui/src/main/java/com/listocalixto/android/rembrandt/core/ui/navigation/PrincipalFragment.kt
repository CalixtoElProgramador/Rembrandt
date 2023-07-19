package com.listocalixto.android.rembrandt.core.ui.navigation

import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class PrincipalFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {
    @get:IdRes
    abstract val navHostFragmentId: Int
    abstract fun navigateToArtworkDetail()
}
