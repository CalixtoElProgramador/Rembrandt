package com.listocalixto.android.rembrandt.presentation.ui.main.home

import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.transition.Hold
import com.listocalixto.android.rembrandt.R
import com.listocalixto.android.rembrandt.databinding.FragmentHomeBinding as Binding
import com.listocalixto.android.rembrandt.presentation.utility.applySharedElementExitTransition
import com.listocalixto.android.rembrandt.presentation.view.adapter.ArtworkAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()

    private var adapter: ArtworkAdapter? = null
    private var binding: Binding? = null
    private var linearProgress: LinearProgressIndicator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Fragment A’s exitTransition can be set any time before Fragment A is
        // replaced with Fragment B. Ensure Hold's duration is set to the same
        // duration as your MaterialContainerTransform.
        exitTransition = Hold().apply {
            duration =
                requireContext().resources.getInteger(R.integer.motion_duration_large).toLong()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        binding = Binding.bind(view)
        binding?.run {
            setupBinding()
            setupRecyclerView()
            initExteriorViews()
            collectUiState()
        }
    }

    private fun Binding.setupBinding() {
        lifecycleOwner = this@HomeFragment.viewLifecycleOwner
        homeViewModel = viewModel
    }

    private fun Binding.setupRecyclerView() {
        adapter =
            ArtworkAdapter(viewModel::onEvent, onArtwork = { artworkId, card, memoryCacheKey ->
                navigateToArtworkDetail(artworkId, card, memoryCacheKey)
            }).also {
                listArtworks.adapter = it
            }
    }

    private fun navigateToArtworkDetail(artworkId: Long, card: View, memoryCacheKey: String?) {
        applySharedElementExitTransition()
        val detailTransitionName = getString(R.string.item_card_detail_transition_name)
        val extras = FragmentNavigatorExtras(card to detailTransitionName)
        val direction = HomeFragmentDirections.toArtworkDetailFragment(
            artworkId,
            memoryCacheKey,
            displayInitialAnimations = true
        )
        findNavController().navigate(direction, extras)
    }

    private fun initExteriorViews() {
        linearProgress = activity?.findViewById(R.id.linearProgress)
    }

    private fun collectUiState() = viewLifecycleOwner.lifecycleScope.launch {
        viewModel.uiState.flowWithLifecycle(lifecycle).collect { state ->
            adapter?.submitList(state.artworks)
            linearProgress?.visibility = if (state.isLoading) VISIBLE else INVISIBLE
        }
    }
}
