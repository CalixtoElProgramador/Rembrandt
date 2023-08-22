package com.listocalixto.android.rembrandt.feature.artworkdetail.content.page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.listocalixto.android.rembrandt.core.ui.extensions.EmphasisType
import com.listocalixto.android.rembrandt.core.ui.extensions.collectFlowWithLifeCycle
import com.listocalixto.android.rembrandt.core.ui.extensions.emphasizes
import com.listocalixto.android.rembrandt.core.ui.extensions.fader
import com.listocalixto.android.rembrandt.core.ui.utility.FragmentPage
import com.listocalixto.android.rembrandt.feature.artworkdetail.ArtworkDetailViewModel
import com.listocalixto.android.rembrandt.feature.artworkdetail.R
import dagger.hilt.android.AndroidEntryPoint
import com.listocalixto.android.rembrandt.core.ui.R as Rui
import com.listocalixto.android.rembrandt.feature.artworkdetail.databinding.FragmentArtworkDescriptionBinding as Binding

@AndroidEntryPoint
internal class ArtworkDescriptionFragment : FragmentPage(R.layout.fragment_artwork_description) {

    override val tabTitleRes: Int = Rui.string.frag_artwork_content_tab_description_text
    override val instance: Fragment = this
    private val viewModel: ArtworkDetailViewModel by viewModels({ requireParentFragment() })

    private var binding: Binding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = Binding.bind(view)
        binding?.run {
            setupBinding()
            collectUiState()
        }
    }

    private fun Binding.setupBinding() {
        lifecycleOwner = this@ArtworkDescriptionFragment.viewLifecycleOwner
        artworkDetailViewModel = viewModel
        fragment = this@ArtworkDescriptionFragment
    }

    private fun Binding.collectUiState() {
        collectFlowWithLifeCycle(viewModel.uiState) { state ->
            if (state.isLoadingTranslation) {
                textDescription.emphasizes(EmphasisType.Disable)
            }
            if (state.triggerTranslationAnimation != null) {
                textDescription.fader(emphasisType = EmphasisType.Medium)
            } else {
                textDescription.post {
                    viewPagerFragment?.updatePagerHeightForChild(root)
                }
            }
        }
    }
}
