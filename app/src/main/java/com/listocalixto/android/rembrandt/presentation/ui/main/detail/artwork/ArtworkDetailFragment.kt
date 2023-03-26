package com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
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

    private val args: ArtworkDetailFragmentArgs by navArgs()
    private val viewModel: ArtworkDetailViewModel by viewModels()
    private val reloadPreviousArtworkOnBackPressed = object : OnBackPressedCallback(false) {
        override fun handleOnBackPressed() {
            viewModel.onEvent(OnBackPressed)
        }
    }
    private val artworkRecommendedAdapter = ArtworkRecommendedAdapter { position ->
        viewModel.onEvent(OnArtworkRecommended(position))
        binding?.run {
            scrollContainer.post {
                scrollContainer.smoothScrollTo(0, 0)
            }
        }
    }

    private var binding: Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            reloadPreviousArtworkOnBackPressed,
        )
    }

    override fun onStart() {
        super.onStart()
        viewModel.onEvent(Start(args.artworkId))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
}
