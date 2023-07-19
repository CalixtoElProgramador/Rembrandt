package com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.content

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.listocalixto.android.rembrandt.R
import com.listocalixto.android.rembrandt.core.ui.adapter.FragmentAdapter
import dagger.hilt.android.AndroidEntryPoint
import com.listocalixto.android.rembrandt.databinding.FragmentArtworkContentBinding as Binding

@AndroidEntryPoint
class ArtworkContentFragment : Fragment(R.layout.fragment_artwork_content) {

    private var adapter: FragmentAdapter? = null
    private var binding: Binding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = Binding.bind(view)
        binding?.run {
            val context = context ?: return@run
            val resources = context.resources ?: return@run
            lifecycleOwner = this@ArtworkContentFragment.viewLifecycleOwner
            setupViewPagerWithTabLayout(resources)
        }
    }

    private fun Binding.setupViewPagerWithTabLayout(resources: Resources) {
        /*val descriptionFragment = ArtworkDescriptionFragment()
        val characteristicsFragment = ArtworkCharacteristicsFragment()
        val fragments = listOf<ArtworkContentPage>(descriptionFragment, characteristicsFragment)
        adapter = FragmentAdapter(fragments, requireParentFragment()).also {
            viewPager.adapter = it
        }
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = resources.getText(fragments[position].tabTitleRes)
        }.attach()*/
    }
}
