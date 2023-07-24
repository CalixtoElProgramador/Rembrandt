package com.listocalixto.android.rembrandt.core.ui.navigation

import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras

abstract class PrincipalFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {
    @get:IdRes
    abstract val navHostFragmentIdRes: Int

    @get:IdRes
    abstract val extendedFabIdRes: Int

    @get:IdRes
    abstract val linearProgressIdRes: Int

    @get:IdRes
    abstract val appBarIdRes: Int
    abstract fun navigateToArtworkDetail(
        artworkId: Long,
        imageMemoryCacheKey: String?,
        shouldShowEnterAnimations: Boolean,
        imageAmbientColor: Int,
        extras: FragmentNavigator.Extras = FragmentNavigatorExtras(),
    )
}
