package com.listocalixto.android.rembrandt.feature.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import com.listocalixto.android.rembrandt.core.ui.adapter.ArtworkUserAdapter
import com.listocalixto.android.rembrandt.core.ui.extensions.applyFadeThroughEnterTransition
import com.listocalixto.android.rembrandt.core.ui.extensions.applyHoldExitTransition
import com.listocalixto.android.rembrandt.core.ui.extensions.collectFlowWithLifeCycle
import com.listocalixto.android.rembrandt.core.ui.extensions.startFragmentTransition
import com.listocalixto.android.rembrandt.core.ui.navigation.PrincipalFragment
import dagger.hilt.android.AndroidEntryPoint
import com.listocalixto.android.rembrandt.core.ui.R as Rui
import com.listocalixto.android.rembrandt.feature.home.databinding.FragmentHomeBinding as Binding
import com.listocalixto.android.rembrandt.core.ui.navigation.BottomNavTabType
import com.listocalixto.android.rembrandt.core.ui.navigation.BottomNavTabType.Home

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()

    private var adapter: ArtworkUserAdapter? = null
    private var binding: Binding? = null

    private val principalFragment: PrincipalFragment?
        get() = ((parentFragment as? NavHostFragment)?.parentFragment as? PrincipalFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyFadeThroughEnterTransition()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        binding = Binding.bind(view)
        binding?.run {
            setupBinding()
            setupRecyclerView()
            // initExteriorViews()
            collectUiState()
            startFragmentTransition()
        }
    }

    private fun Binding.setupBinding() {
        lifecycleOwner = this@HomeFragment.viewLifecycleOwner
        homeViewModel = viewModel
    }

    private fun Binding.setupRecyclerView() {
        adapter =
            ArtworkUserAdapter(
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
        applyHoldExitTransition()
        val detailTransitionName = getString(Rui.string.item_card_detail_transition_name)
        val extras = FragmentNavigatorExtras(card to detailTransitionName)
        principalFragment?.navigateToArtworkDetail(
            artworkId = artworkId,
            imageMemoryCacheKey = memoryCacheKey,
            shouldShowEnterAnimations = true,
            imageAmbientColor = gradientColor,
            comesFrom = Home,
            extras = extras,
        )
    }

    /*private fun initExteriorViews() {
        linearProgress = activity?.findViewById(Rui.id.linearProgress)
        appBar = activity?.findViewById(Rui.id.appBar)
    }*/

    private fun collectUiState() {
        collectFlowWithLifeCycle(viewModel.uiState) { state ->
            adapter?.submitList(state.artworks)
            // linearProgress?.visibility = if (state.isLoading) VISIBLE else INVISIBLE
        }
    }
}
