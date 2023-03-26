package com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.color.MaterialColors
import com.google.android.material.transition.MaterialContainerTransform
import com.listocalixto.android.rembrandt.R
import com.listocalixto.android.rembrandt.presentation.adapter.ArtworkRecommendedAdapter
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.ArtworkDetailUiEvent.OnArtworkRecommended
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.ArtworkDetailUiEvent.OnBackPressed
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.ArtworkDetailUiEvent.Start
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.listocalixto.android.rembrandt.databinding.FragmentArtworkDetailBinding as Binding

@AndroidEntryPoint
class ArtworkDetailFragment : Fragment(R.layout.fragment_artwork_detail) {

    private val viewModel: ArtworkDetailViewModel by viewModels()
    private val reloadPreviousArtworkOnBackPressed = object : OnBackPressedCallback(false) {
        override fun handleOnBackPressed() {
            viewModel.onEvent(OnBackPressed)
        }
    }
    private val artworkRecommendedAdapter = ArtworkRecommendedAdapter { artworkId ->
        viewModel.onEvent(OnArtworkRecommended(artworkId))
        binding?.run {
            scrollContainer.post {
                scrollContainer.smoothScrollTo(0, 0)
            }
            listRecommended.post {
                listRecommended.smoothScrollToPosition(0)
            }
        }
    }

    private var binding: Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.navHostMainFragment
            duration = resources.getInteger(R.integer.motion_duration_large).toLong()
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(
                MaterialColors.getColor(
                    requireContext(),
                    com.google.android.material.R.attr.colorSurface,
                    Color.MAGENTA,
                ),
            )
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            reloadPreviousArtworkOnBackPressed,
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
            listRecommended.adapter = artworkRecommendedAdapter
            collectUiState()
        }
    }

    private fun Binding.collectUiState() = viewLifecycleOwner.lifecycleScope.launch {
        viewModel.uiState.flowWithLifecycle(lifecycle).collect { state ->
            artworkRecommendedAdapter.submitList(state.artworksRecommended)
            reloadPreviousArtworkOnBackPressed.isEnabled = state.dataInTheBackStack.isNotEmpty()
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
