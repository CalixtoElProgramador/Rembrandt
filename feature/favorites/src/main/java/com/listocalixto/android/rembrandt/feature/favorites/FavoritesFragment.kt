package com.listocalixto.android.rembrandt.feature.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.listocalixto.android.rembrandt.core.ui.adapter.ArtworkUserAdapter
import com.listocalixto.android.rembrandt.core.ui.extensions.applyFadeThroughEnterTransition
import com.listocalixto.android.rembrandt.core.ui.extensions.collectFlowWithLifeCycle
import com.listocalixto.android.rembrandt.core.ui.navigation.PrincipalFragment
import com.listocalixto.android.rembrandt.feature.favorites.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val viewModel by viewModels<FavoritesViewModel>()

    private val principalFragment: PrincipalFragment?
        get() = ((parentFragment as? NavHostFragment)?.parentFragment as? PrincipalFragment)

    private var binding: FragmentFavoritesBinding? = null
    private var adapter: ArtworkUserAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyFadeThroughEnterTransition()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoritesBinding.bind(view)
        binding?.run {
            lifecycleOwner = this@FavoritesFragment.viewLifecycleOwner
            favoritesViewModel = viewModel
            setupRecyclerView()
            collectUiState()
        }
    }

    private fun FragmentFavoritesBinding.setupRecyclerView() {
        adapter = ArtworkUserAdapter(
            onArtworkClick = ::navigateToArtworkDetail,
            onFavoriteClick = viewModel::onFavoriteClick,
        ).also {
            listArtworks.adapter = it
        }
    }

    private fun navigateToArtworkDetail(
        artworkId: Long,
        card: View,
        memoryCacheKey: String?,
        gradientColor: Int,
    ) {
        /*applyHoldExitTransition()
        val detailTransitionName = getString(Rui.string.item_card_detail_transition_name)
        val extras = FragmentNavigatorExtras(card to detailTransitionName)
        val direction = MainModuleGraphDirections.actionGlobalArtworkDetailFragment(
            gradientColor = gradientColor,
            artworkId = artworkId,
            memoryCacheKey = memoryCacheKey,
            displayInitialAnimations = true,
        )
        try {
            findNavController().navigate(direction, extras)
        } catch (_: IllegalArgumentException) {
        }*/
        principalFragment?.navigateToArtworkDetail()
    }

    private fun collectUiState() {
        collectFlowWithLifeCycle(viewModel.uiState) { state ->
            adapter?.submitList(state.artworks)
        }
    }
}
