package com.listocalixto.android.rembrandt.feature.artworkdetail.content.page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.listocalixto.android.rembrandt.core.ui.extensions.collectFlowWithLifeCycle
import com.listocalixto.android.rembrandt.core.ui.navigation.PrincipalFragment
import com.listocalixto.android.rembrandt.core.ui.utility.ArtworkContentPage
import com.listocalixto.android.rembrandt.feature.artworkdetail.ArtworkDetailViewModel
import com.listocalixto.android.rembrandt.feature.artworkdetail.R
import com.listocalixto.android.rembrandt.presentation.utility.extentions.EmphasisType
import com.listocalixto.android.rembrandt.presentation.utility.extentions.emphasizes
import com.listocalixto.android.rembrandt.presentation.utility.extentions.fader
import dagger.hilt.android.AndroidEntryPoint
import com.listocalixto.android.rembrandt.core.ui.R as Rui
import com.listocalixto.android.rembrandt.feature.artworkdetail.databinding.FragmentArtworkDescriptionBinding as Binding

@AndroidEntryPoint
class ArtworkDescriptionFragment :
    Fragment(R.layout.fragment_artwork_description),
    ArtworkContentPage {

    override val tabTitleRes: Int = Rui.string.frag_artwork_content_tab_description_text
    override val instance: Fragment = this
    private val viewModel: ArtworkDetailViewModel by viewModels({ requireParentFragment() })

    private val principalFragment: PrincipalFragment?
        get() = ((parentFragment as? NavHostFragment)?.parentFragment as? PrincipalFragment)

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
        principalFragment?.let {
            extendedFab = activity?.findViewById(it.extendedFabIdRes)
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
            }
        }
    }
}
