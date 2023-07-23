package com.listocalixto.android.rembrandt.presentation.ui.display

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.listocalixto.android.rembrandt.R
import com.listocalixto.android.rembrandt.core.ui.extensions.applySharedElementTransition
import com.listocalixto.android.rembrandt.core.ui.extensions.collectFlowWithLifeCycle
import com.listocalixto.android.rembrandt.core.ui.extensions.startTransition
import com.listocalixto.android.rembrandt.databinding.FragmentDisplayArtworkBinding

class DisplayArtworkFragment : Fragment(R.layout.fragment_display_artwork) {

    private val viewModel: DisplayArtworkViewModel by viewModels()

    private var binding: FragmentDisplayArtworkBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applySharedElementTransition()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        binding = FragmentDisplayArtworkBinding.bind(view)
        binding?.run {
            initViews()
            collectUiState()
        }
    }

    private fun FragmentDisplayArtworkBinding.initViews() {
        lifecycleOwner = viewLifecycleOwner
        displayArtworkViewModel = viewModel
        image.maxZoom *= 2
    }

    private fun FragmentDisplayArtworkBinding.collectUiState() {
        collectFlowWithLifeCycle(viewModel.uiState) { state ->
            if (state.shouldAnimateZoom) {
                image.setZoom(state.scale, state.focusX, state.focusY)
                image.load(state.imageUrl) {
                    placeholderMemoryCacheKey(state.memoryCacheKey)
                    listener(
                        onSuccess = { _, _ -> startTransition() },
                        onError = { _, _ -> startTransition() },
                    )
                }
                viewModel.onZoomAnimationTriggered()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.image?.savePreviousImageValues()
    }

    companion object {
        const val MEMORY_CACHE_KEY_ID_KEY = "previousImageMemoryCacheKey"
        const val ALT_TEXT_KEY = "altText"
        const val IMAGE_URL_KEY = "hightResolutionImageUrl"
        const val TOUCH_POSITION_X_KEY = "touchPositionX"
        const val TOUCH_POSITION_Y_KEY = "touchPositionY"
        const val ZOOM_KEY = "zoom"
    }
}
