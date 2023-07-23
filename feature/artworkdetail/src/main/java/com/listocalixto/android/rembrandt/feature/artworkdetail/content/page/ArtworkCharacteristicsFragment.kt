package com.listocalixto.android.rembrandt.feature.artworkdetail.content.page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.listocalixto.android.rembrandt.core.ui.R as Rui
import com.listocalixto.android.rembrandt.feature.artworkdetail.databinding.FragmentArtworkCharacteristicsBinding as Binding
import com.listocalixto.android.rembrandt.feature.artworkdetail.ArtworkDetailViewModel
import com.listocalixto.android.rembrandt.feature.artworkdetail.R
import com.listocalixto.android.rembrandt.core.ui.utility.ArtworkContentPage

class ArtworkCharacteristicsFragment :
    Fragment(R.layout.fragment_artwork_characteristics),
    ArtworkContentPage {

    override val tabTitleRes: Int = Rui.string.frag_artwork_content_tab_characteristics_text
    override val instance: Fragment = this

    private val viewModel: ArtworkDetailViewModel by viewModels({ requireParentFragment() })

    private var binding: Binding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = Binding.bind(view)
        binding?.run {
            lifecycleOwner = viewLifecycleOwner
        }
    }
}
