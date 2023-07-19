package com.listocalixto.android.rembrandt.navigation.principal

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.listocalixto.android.rembrandt.DisplayImageFragmentGraphDirections
import com.listocalixto.android.rembrandt.R
import com.listocalixto.android.rembrandt.core.ui.extensions.applyFadeThroughExitTransition
import com.listocalixto.android.rembrandt.core.ui.extensions.getNavHost
import com.listocalixto.android.rembrandt.core.ui.navigation.PrincipalFragment
import com.listocalixto.android.rembrandt.databinding.FragmentPrincipalBinding
import com.listocalixto.android.rembrandt.feature.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
internal class PrincipalFragmentImpl :
    PrincipalFragment(R.layout.fragment_principal),
    NavController.OnDestinationChangedListener {

    private val viewModel by activityViewModels<PrincipalViewModelImpl>()
    private val currentNavigationFragment: Fragment?
        get() = childFragmentManager
            .findFragmentById(R.id.navHostMainFragment)
            ?.childFragmentManager
            ?.fragments
            ?.first()

    private val homeFragment: Fragment?
        get() = currentNavigationFragment as? HomeFragment

    private var binding: FragmentPrincipalBinding? = null
    private var appBarDefaultBackground: Drawable? = null
    private var lastGradientColor: Int? = null

    override val navHostFragmentId: Int
        get() = binding?.navHostMainFragment?.id ?: -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPrincipalBinding.bind(view)
        binding?.run {
            lifecycleOwner = this@PrincipalFragmentImpl.viewLifecycleOwner
            mainViewModel = viewModel
            if (appBarDefaultBackground == null) {
                appBarDefaultBackground = appBar.background
            }
            val navHostFragment = getNavHost(navHostMainFragment.id)
            val navController = navHostFragment.navController
            bottomNav.setupWithNavController(navController)
            navController.addOnDestinationChangedListener(this@PrincipalFragmentImpl)
            collectNavigateState()
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.uiState.flowWithLifecycle(lifecycle).collect { state ->
                    linearProgress.isVisible = state.isLoading
                }
            }
        }
    }

    override fun navigateToArtworkDetail() {
        val direction = DisplayImageFragmentGraphDirections.showArtworkDetail(1)
        currentNavigationFragment?.findNavController()?.navigate(direction)
    }

    private fun collectNavigateState() {
        /*collectFlowWithLifeCycle(viewModel.navigationState) { state ->
            when (state) {
                is NavigateToDisplayImageFragment -> {
                    val image = state.sharedElement
                    val args = state.args
                    applyFadeThroughExitTransition()
                    val transitionName = getString(Rui.string.display_artwork_transition_name)
                    val extras = FragmentNavigatorExtras(image to transitionName)
                    val directions = actionMainFragmentToDisplayArtworkFragment(
                        hightResolutionImageUrl = args.hightResolutionImageUrl,
                        altText = args.altText,
                        previousImageMemoryCacheKey = args.previousImageMemoryCacheKey,
                        touchPositionX = args.touchPositionX,
                        touchPositionY = args.touchPositionY,
                        zoom = args.zoom,
                    )
                    findNavController().navigate(directions, extras)
                }
            }
        }*/
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?,
    ) {
        (binding ?: return).run {
            when (destination.id) {
                R.id.explore_graph -> {
                    extendedFab.show()
                    appBar.background = appBarDefaultBackground
                    homeFragment?.apply {
                        applyFadeThroughExitTransition()
                    }
                }

                R.id.home_graph -> {
                    appBar.background = appBarDefaultBackground
                    extendedFab.hide()
                }

                /*R.id.artworkDetailFragment -> {
                    extendedFab.apply {
                        icon = ResourcesCompat.getDrawable(
                            context.resources,
                            R.drawable.ic_translate,
                            activity?.theme,
                        )
                    }
                    bottomNav.setOnItemReselectedListener {}
                    arguments?.let {
                        val args = ArtworkDetailFragmentArgs.fromBundle(arguments)
                        if (isDarkMode()) {
                            val currentColorBackground: Int =
                                when (val currentAppBarBackground = appBar.background) {
                                    is MaterialShapeDrawable -> {
                                        currentAppBarBackground.resolvedTintColor
                                    }

                                    is ColorDrawable -> {
                                        currentAppBarBackground.color
                                    }

                                    else -> {
                                        Color.MAGENTA
                                    }
                                }
                            lastGradientColor = args.gradientColor
                            appBar.colorize(
                                currentColor = currentColorBackground,
                                newColor = args.gradientColor,
                            )
                        }
                    }
                }*/

                R.id.favorites_graph -> {
                    extendedFab.hide()
                    appBar.background = appBarDefaultBackground
                    homeFragment?.apply {
                        applyFadeThroughExitTransition()
                    }
                }

                else -> {
                }
            }
        }
    }
}
