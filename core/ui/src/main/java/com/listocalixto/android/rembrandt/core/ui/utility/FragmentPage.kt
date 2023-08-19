package com.listocalixto.android.rembrandt.core.ui.utility

import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

abstract class FragmentPage(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {
    @get:StringRes
    abstract val tabTitleRes: Int
    abstract val instance: Fragment
    val viewPagerFragment: FragmentViewPager?
        get() = parentFragmentManager.fragments.takeIf { it.isNotEmpty() }
            ?.get(0) as FragmentViewPager?

    override fun onResume() {
        super.onResume()
        view?.let { viewPagerFragment?.updatePagerHeightForChild(it) }
    }
}
