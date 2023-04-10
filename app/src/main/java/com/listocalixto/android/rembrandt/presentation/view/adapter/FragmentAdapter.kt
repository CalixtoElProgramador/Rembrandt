package com.listocalixto.android.rembrandt.presentation.view.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.content.ArtworkContentPage

class FragmentAdapter(
    private val fragments: List<ArtworkContentPage>,
    fragmentContainer: Fragment
) : FragmentStateAdapter(fragmentContainer) {

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position].instance
    }
}
