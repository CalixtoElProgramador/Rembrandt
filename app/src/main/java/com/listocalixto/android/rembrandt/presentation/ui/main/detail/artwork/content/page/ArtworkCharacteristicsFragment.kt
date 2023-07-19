package com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.content.page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.listocalixto.android.rembrandt.R
import com.listocalixto.android.rembrandt.databinding.FragmentArtworkCharacteristicsBinding
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.ArtworkDetailViewModel
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.content.ArtworkContentPage
import com.listocalixto.android.rembrandt.core.ui.R as Rui

class ArtworkCharacteristicsFragment :
    Fragment(R.layout.fragment_artwork_characteristics),
    ArtworkContentPage {

    override val tabTitleRes: Int = Rui.string.frag_artwork_content_tab_characteristics_text
    override val instance: Fragment = this

    private val viewModel: ArtworkDetailViewModel by viewModels({ requireParentFragment() })

    private var binding: FragmentArtworkCharacteristicsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArtworkCharacteristicsBinding.bind(view)
        binding?.run {
            lifecycleOwner = viewLifecycleOwner
        }
    }
}
