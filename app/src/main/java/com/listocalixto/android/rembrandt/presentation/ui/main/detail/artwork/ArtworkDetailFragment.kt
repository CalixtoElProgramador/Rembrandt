package com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.listocalixto.android.rembrandt.R
import com.listocalixto.android.rembrandt.databinding.FragmentArtworkDetailBinding as Binding
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.ArtworkDetailUiEvent.TranslateContent
import com.listocalixto.android.rembrandt.presentation.utility.ColorContainerType
import com.listocalixto.android.rembrandt.presentation.utility.SnackbarDuration.SHORT
import com.listocalixto.android.rembrandt.presentation.utility.UiText
import com.listocalixto.android.rembrandt.presentation.utility.applyFadeThroughEnterTransition
import com.listocalixto.android.rembrandt.presentation.utility.applyFadeThroughExitTransition
import com.listocalixto.android.rembrandt.presentation.utility.applySharedElementEnterTransition
import com.listocalixto.android.rembrandt.presentation.utility.extentions.adjustSizeAccordingScroll
import com.listocalixto.android.rembrandt.presentation.utility.extentions.fader
import com.listocalixto.android.rembrandt.presentation.utility.showSnackbar
import com.listocalixto.android.rembrandt.presentation.view.adapter.ArtworkRecommendedAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArtworkDetailFragment : Fragment(R.layout.fragment_artwork_detail) {

    private val viewModel: ArtworkDetailViewModel by viewModels()
    private val artworkRecommendedAdapter = ArtworkRecommendedAdapter { artworkId, memoryCacheKey ->
        navigateToSelf(artworkId, memoryCacheKey)
    }

    private var binding: Binding? = null
    private var extendedFab: ExtendedFloatingActionButton? = null
    private var linearProgressIndicator: LinearProgressIndicator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val colorSurface = com.google.android.material.R.attr.colorSurface
        applyFadeThroughEnterTransition()
        applySharedElementEnterTransition(
            drawingViewIdRes = R.id.navHostMainFragment,
            colorContainerType = ColorContainerType.AllContainerColors(colorSurface)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        binding = Binding.bind(view)
        binding?.run {
            setupInternalViews()
            initExternalViews()
            setupExtendedFab()
            collectUiState()
        }
    }

    private fun Binding.setupExtendedFab() {
        extendedFab?.adjustSizeAccordingScroll(scrollContainer)
        extendedFab?.setOnClickListener { requestTranslation() }
    }

    private fun Binding.setupInternalViews() {
        lifecycleOwner = this@ArtworkDetailFragment.viewLifecycleOwner
        artworkDetailViewModel = viewModel
        onChipFavorite = ArtworkDetailUiEvent.OnChipFavorite
        listRecommended.adapter = artworkRecommendedAdapter
    }

    private fun initExternalViews() {
        extendedFab = activity?.findViewById(R.id.extendedFab)
        linearProgressIndicator = activity?.findViewById(R.id.linearProgress)
    }

    private fun Binding.requestTranslation() {
        val isTranslationLoading = viewModel.uiState.value.loadingTranslation
        val stringRes = R.string.frag_artwork_detail_wait_until_translation_is_ready
        if (isTranslationLoading) {
            val snackbarMessage = UiText.StringResource(stringRes)
            showSnackbar(root, snackbarMessage, duration = SHORT, anchorView = extendedFab)
        } else {
            viewModel.onEvent(TranslateContent)
        }
    }

    private fun navigateToSelf(artworkId: Long, memoryCacheKey: String?) {
        applyFadeThroughExitTransition()
        val directions = ArtworkDetailFragmentDirections.actionArtworkDetailFragmentSelf(
            artworkId,
            memoryCacheKey
        )
        findNavController().navigate(directions)
    }

    private fun Binding.collectUiState() = viewLifecycleOwner.lifecycleScope.launch {
        val context = context ?: return@launch
        val resources = context.resources ?: return@launch
        viewModel.uiState.flowWithLifecycle(lifecycle).collect { state ->
            if (state.artwork != null) {
                (view?.parent as? ViewGroup)?.doOnPreDraw {
                    startPostponedEnterTransition()
                    extendedFab?.show()
                }
            }
            artworkRecommendedAdapter.submitList(state.artworksRecommended)
            extendedFab?.text = if (state.isTranslationDisplayed) {
                resources.getText(R.string.frag_artwork_detail_extended_fab_show_original)
            } else {
                resources.getText(R.string.frag_artwork_detail_extended_fab_translate)
            }
            if (state.triggerRefreshAnimation != null) {
                textCategory.fader(viewTrigger = extendedFab)
                textTitle.fader(viewTrigger = extendedFab)
                viewModel.onEvent(ArtworkDetailUiEvent.RefreshAnimationTriggered)
            }
            val errorMessage = state.errorMessage
            if (errorMessage != null) {
                showSnackbar(root, errorMessage, SHORT) {
                    it.dismiss()
                }
                viewModel.onEvent(ArtworkDetailUiEvent.ErrorMessageTriggered)
            }
            if (state.loadingTranslation) {
                linearProgressIndicator?.visibility = View.VISIBLE
            } else {
                linearProgressIndicator?.visibility = View.GONE
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.onEvent(ArtworkDetailUiEvent.SaveCurrentArtworkId)
    }

    companion object {
        // The name for this key needs to be exactly the same as was defined in safe args.
        const val ARTWORK_ID_KEY = "artworkId"
        const val MEMORY_CACHE_KEY_ID_KEY = "memoryCacheKey"
        const val ARTWORK_ID_DEFAULT_VALUE = -1L
    }
}
