package com.listocalixto.android.rembrandt.core.ui.utility

import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class FragmentViewPager(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {
    abstract fun updatePagerHeightForChild(view: View)
}
