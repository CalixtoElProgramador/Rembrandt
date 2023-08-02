package com.listocalixto.android.rembrandt.feature.displayimage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.listocalixto.android.rembrandt.core.ui.extensions.applySharedElementTransition
import com.listocalixto.android.rembrandt.core.ui.extensions.collectFlowWithLifeCycle
import com.listocalixto.android.rembrandt.core.ui.extensions.startFragmentTransition
import com.listocalixto.android.rembrandt.feature.displayimage.databinding.FragmentDisplayImageBinding as Binding

class DisplayImageFragment : Fragment(R.layout.fragment_display_image) {

    private val viewModel: DisplayImageViewModel by viewModels()

    private var binding: Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applySharedElementTransition()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        binding = Binding.bind(view)
        binding?.run {
            initViews()
            collectUiState()
        }
    }

    private fun Binding.initViews() {
        lifecycleOwner = viewLifecycleOwner
        displayArtworkViewModel = viewModel
    }

    private fun Binding.collectUiState() {
        collectFlowWithLifeCycle(viewModel.uiState) { state ->
            if (state.shouldAnimateZoom) {
                image.setZoom(state.scale, state.focusX, state.focusY)
                image.load(state.imageUrl) {
                    placeholderMemoryCacheKey(state.memoryCacheKey)
                    listener(
                        onSuccess = { _, _ -> startFragmentTransition() },
                        onError = { _, _ -> startFragmentTransition() },
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
        const val ALT_TEXT_KEY = "alternativeText"
        const val IMAGE_URL_KEY = "imageUrl"
        const val TOUCH_POSITION_X_KEY = "touchPositionX"
        const val TOUCH_POSITION_Y_KEY = "touchPositionY"
        const val ZOOM_KEY = "zoom"
    }
}
