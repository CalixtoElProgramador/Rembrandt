package com.listocalixto.android.rembrandt.core.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter(
    private val fragments: List<Fragment>,
    fragmentContainer: Fragment,
) : FragmentStateAdapter(fragmentContainer) {

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}
