package com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.content

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayoutMediator
import com.listocalixto.android.rembrandt.R
import com.listocalixto.android.rembrandt.databinding.FragmentArtworkContentBinding
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.ArtworkDetailViewModel
import com.listocalixto.android.rembrandt.presentation.view.adapter.FragmentAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArtworkContentFragment : Fragment(R.layout.fragment_artwork_content) {

    private val viewModel: ArtworkDetailViewModel by viewModels({ requireParentFragment() })

    private var adapter: FragmentAdapter? = null
    private var binding: FragmentArtworkContentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArtworkContentBinding.bind(view)
        binding?.run {
            val context = context ?: return@run
            val resources = context.resources ?: return@run
            lifecycleOwner = this@ArtworkContentFragment.viewLifecycleOwner
            val descriptionFragment = ArtworkDescriptionFragment()
            val fragments = listOf<ArtworkContentPage>(descriptionFragment)
            adapter = FragmentAdapter(fragments, requireParentFragment()).also {
                viewPager.adapter = it
            }
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = resources.getText(fragments[position].tabTitleRes)
            }.attach()
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.uiState.flowWithLifecycle(lifecycle).collect { state ->
                    state.artwork
                }
            }
        }
    }
}
