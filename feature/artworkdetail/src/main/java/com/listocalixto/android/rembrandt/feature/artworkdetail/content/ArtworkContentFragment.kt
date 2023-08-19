package com.listocalixto.android.rembrandt.feature.artworkdetail.content

import android.os.Bundle
import android.view.View
import android.view.View.MeasureSpec.EXACTLY
import android.view.View.MeasureSpec.UNSPECIFIED
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.listocalixto.android.rembrandt.core.ui.adapter.FragmentAdapter
import com.listocalixto.android.rembrandt.core.ui.extensions.animateHeightChanges
import com.listocalixto.android.rembrandt.core.ui.utility.FragmentViewPager
import com.listocalixto.android.rembrandt.feature.artworkdetail.ArtworkDetailViewModel
import com.listocalixto.android.rembrandt.feature.artworkdetail.R
import dagger.hilt.android.AndroidEntryPoint
import com.listocalixto.android.rembrandt.feature.artworkdetail.databinding.FragmentArtworkContentBinding as Binding

@AndroidEntryPoint
class ArtworkContentFragment : FragmentViewPager(R.layout.fragment_artwork_content) {

    private var binding: Binding? = null
    private val viewModel: ArtworkDetailViewModel by viewModels({ requireParentFragment() })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = Binding.bind(view)
        binding?.run {
            lifecycleOwner = this@ArtworkContentFragment.viewLifecycleOwner
            setupViewPagerWithTabLayout()
        }
    }

    private fun Binding.setupViewPagerWithTabLayout() {
        val fragments = viewModel.contentFragments
        FragmentAdapter(fragments, requireParentFragment()).also { viewPager.adapter = it }
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = resources.getText(fragments[position].tabTitleRes)
        }.attach()
    }

    override fun updatePagerHeightForChild(view: View) {
        val viewPager = binding?.viewPager ?: return
        view.post {
            val widthMeasure = View.MeasureSpec.makeMeasureSpec(view.width, EXACTLY)
            val heightMeasure = View.MeasureSpec.makeMeasureSpec(0, UNSPECIFIED)
            view.measure(widthMeasure, heightMeasure)
            if (viewPager.layoutParams.height != view.measuredHeight) {
                viewPager.animateHeightChanges(view.measuredHeight)
            }
        }
    }
}
