package com.listocalixto.android.rembrandt.presentation.ui.main.home

import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.listocalixto.android.rembrandt.R
import com.listocalixto.android.rembrandt.presentation.utility.extentions.applyFadeThroughEnterTransition
import com.listocalixto.android.rembrandt.presentation.utility.extentions.applyHoldExitTransition
import com.listocalixto.android.rembrandt.presentation.utility.extentions.collectFlowWithLifeCycle
import com.listocalixto.android.rembrandt.presentation.view.adapter.ArtworkAdapter
import dagger.hilt.android.AndroidEntryPoint
import com.listocalixto.android.rembrandt.databinding.FragmentHomeBinding as Binding

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()

    private var adapter: ArtworkAdapter? = null
    private var binding: Binding? = null
    private var linearProgress: LinearProgressIndicator? = null
    private var appBar: AppBarLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyFadeThroughEnterTransition()
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
            ArtworkAdapter(
                viewModel::onEvent,
                onArtwork = { artworkId, card, memoryCacheKey, gradientColor ->
                    navigateToArtworkDetail(artworkId, card, memoryCacheKey, gradientColor)
                },
            ).also {
                listArtworks.adapter = it
            }
    }

    private fun navigateToArtworkDetail(
        artworkId: Long,
        card: View,
        memoryCacheKey: String?,
        gradientColor: Int,
    ) {
        applyHoldExitTransition()
        val detailTransitionName = getString(R.string.item_card_detail_transition_name)
        val extras = FragmentNavigatorExtras(card to detailTransitionName)
        val direction = HomeFragmentDirections.toArtworkDetailFragment(
            gradientColor = gradientColor,
            artworkId = artworkId,
            memoryCacheKey = memoryCacheKey,
            displayInitialAnimations = true,
        )
        try {
            findNavController().navigate(direction, extras)
        } catch (e: IllegalArgumentException) {
        }
    }

    private fun initExteriorViews() {
        linearProgress = activity?.findViewById(R.id.linearProgress)
        appBar = activity?.findViewById(R.id.appBar)
    }

    private fun collectUiState() {
        collectFlowWithLifeCycle(viewModel.uiState) { state ->
            adapter?.submitList(state.artworks)
            linearProgress?.visibility = if (state.isLoading) VISIBLE else INVISIBLE
        }
    }

    fun here() {
        Toast.makeText(requireContext(), "Here", Toast.LENGTH_SHORT).show()
    }
}
