package com.listocalixto.android.rembrandt.feature.artworkdetail.content.page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.listocalixto.android.rembrandt.feature.artworkdetail.ArtworkDetailViewModel
import com.listocalixto.android.rembrandt.feature.artworkdetail.R
import com.listocalixto.android.rembrandt.feature.artworkdetail.content.ArtworkContentPage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.listocalixto.android.rembrandt.core.ui.R as Rui
import com.listocalixto.android.rembrandt.feature.artworkdetail.databinding.FragmentArtworkDescriptionBinding as Binding

@AndroidEntryPoint
class ArtworkDescriptionFragment :
    Fragment(R.layout.fragment_artwork_description),
    ArtworkContentPage {

    override val tabTitleRes: Int = Rui.string.frag_artwork_content_tab_description_text
    override val instance: Fragment = this
    private val viewModel: ArtworkDetailViewModel by viewModels({ requireParentFragment() })

    private var binding: Binding? = null
    var extendedFab: ExtendedFloatingActionButton? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = Binding.bind(view)
        binding?.run {
            setupBinding()
            initExternalViews()
            collectUiState()
        }
    }

    private fun initExternalViews() {
        // extendedFab = activity?.findViewById(R.id.extendedFab)
    }

    private fun Binding.setupBinding() {
        lifecycleOwner = this@ArtworkDescriptionFragment.viewLifecycleOwner
        artworkDetailViewModel = viewModel
        fragment = this@ArtworkDescriptionFragment
    }

    private fun Binding.collectUiState() =
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.flowWithLifecycle(lifecycle).collect { state ->
                /*if (state.triggerRefreshAnimation != null) {
                    textDescription.fader(extendedFab)
                }*/
            }
        }
}
