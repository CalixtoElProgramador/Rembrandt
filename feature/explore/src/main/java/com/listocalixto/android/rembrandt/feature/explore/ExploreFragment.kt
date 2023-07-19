package com.listocalixto.android.rembrandt.feature.explore

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.listocalixto.android.rembrandt.feature.explore.databinding.FragmentExploreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : Fragment(R.layout.fragment_explore) {

    private val viewModel by viewModels<ExploreViewModel>()

    private var binding: FragmentExploreBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExploreBinding.bind(view)
        binding?.run {
            lifecycleOwner = this@ExploreFragment.viewLifecycleOwner
            exploreViewModel = viewModel
        }
    }
}
