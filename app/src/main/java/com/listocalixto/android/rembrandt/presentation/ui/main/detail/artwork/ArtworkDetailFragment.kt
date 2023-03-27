package com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.listocalixto.android.rembrandt.R
import com.listocalixto.android.rembrandt.presentation.adapter.ArtworkRecommendedAdapter
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.ArtworkDetailUiEvent.Start
import com.listocalixto.android.rembrandt.presentation.ui.shared.utility.ColorContainerType
import com.listocalixto.android.rembrandt.presentation.ui.shared.utility.applyFadeThroughEnterTransition
import com.listocalixto.android.rembrandt.presentation.ui.shared.utility.applyFadeThroughExitTransition
import com.listocalixto.android.rembrandt.presentation.ui.shared.utility.applySharedElementEnterTransition
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.listocalixto.android.rembrandt.databinding.FragmentArtworkDetailBinding as Binding

@AndroidEntryPoint
class ArtworkDetailFragment : Fragment(R.layout.fragment_artwork_detail) {

    private val viewModel: ArtworkDetailViewModel by viewModels()
    private val artworkRecommendedAdapter = ArtworkRecommendedAdapter { artworkId ->
        navigateToArtworkDetailFragment(artworkId)
    }

    private var binding: Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val colorSurface = com.google.android.material.R.attr.colorSurface
        applyFadeThroughEnterTransition()
        applySharedElementEnterTransition(
            drawingViewIdRes = R.id.navHostMainFragment,
            colorContainerType = ColorContainerType.AllContainerColors(colorSurface),
        )
    }

    override fun onStart() {
        super.onStart()
        viewModel.onEvent(Start)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        binding = Binding.bind(view)
        binding?.run {
            lifecycleOwner = this@ArtworkDetailFragment.viewLifecycleOwner
            artworkDetailViewModel = viewModel
            onChipFavorite = ArtworkDetailUiEvent.OnChipFavorite
            listRecommended.adapter = artworkRecommendedAdapter
            collectUiState()
        }
    }

    private fun navigateToArtworkDetailFragment(artworkId: Long) {
        applyFadeThroughExitTransition()
        val directions = ArtworkDetailFragmentDirections.actionArtworkDetailFragmentSelf(artworkId)
        findNavController().navigate(directions)
    }

    private fun Binding.collectUiState() = viewLifecycleOwner.lifecycleScope.launch {
        viewModel.uiState.flowWithLifecycle(lifecycle).collect { state ->
            artworkRecommendedAdapter.submitList(state.artworksRecommended)
            // reloadPreviousArtworkOnBackPressed.isEnabled = state.dataInTheBackStack.isNotEmpty()
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.onEvent(ArtworkDetailUiEvent.SaveCurrentArtworkId)
    }

    companion object {
        // The name for this key needs to be exactly the same as was defined in safe args.
        const val ARTWORK_ID_KEY = "artworkId"
        const val ARTWORK_ID_DEFAULT_VALUE = -1L
    }
}
