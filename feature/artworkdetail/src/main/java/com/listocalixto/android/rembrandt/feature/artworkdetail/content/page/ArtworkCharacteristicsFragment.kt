package com.listocalixto.android.rembrandt.feature.artworkdetail.content.page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.listocalixto.android.rembrandt.core.ui.adapter.ArtworkCharacteristicAdapter
import com.listocalixto.android.rembrandt.core.ui.extensions.EmphasisType
import com.listocalixto.android.rembrandt.core.ui.extensions.collectFlowWithLifeCycle
import com.listocalixto.android.rembrandt.core.ui.extensions.emphasizes
import com.listocalixto.android.rembrandt.core.ui.extensions.fader
import com.listocalixto.android.rembrandt.core.ui.utility.FragmentPage
import com.listocalixto.android.rembrandt.feature.artworkdetail.ArtworkDetailViewModel
import com.listocalixto.android.rembrandt.feature.artworkdetail.R
import com.listocalixto.android.rembrandt.core.ui.R as Rui
import com.listocalixto.android.rembrandt.feature.artworkdetail.databinding.FragmentArtworkCharacteristicsBinding as Binding

internal class ArtworkCharacteristicsFragment :
    FragmentPage(R.layout.fragment_artwork_characteristics) {

    override val tabTitleRes: Int = Rui.string.frag_artwork_content_tab_characteristics_text
    override val instance: Fragment = this
    private val viewModel: ArtworkDetailViewModel by viewModels({ requireParentFragment() })
    private val adapter = ArtworkCharacteristicAdapter()

    private var binding: Binding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = Binding.bind(view)
        binding?.run {
            lifecycleOwner = viewLifecycleOwner
            characteristics.setHasFixedSize(false)
            characteristics.adapter = adapter
            collectUiState()
        }
    }

    private fun Binding.collectUiState() {
        collectFlowWithLifeCycle(viewModel.uiState) { state ->
            adapter.submitList(state.characteristics)
            if (state.isLoadingTranslation) {
                characteristics.emphasizes(EmphasisType.Disable)
            }
            if (state.triggerTranslationAnimation != null) {
                characteristics.fader(alphaTarget = 1.0f)
            } else {
                characteristics.post {
                    viewPagerFragment?.updatePagerHeightForChild(root)
                }
            }
        }
    }
}
