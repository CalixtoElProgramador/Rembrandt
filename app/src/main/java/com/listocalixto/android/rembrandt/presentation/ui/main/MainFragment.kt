package com.listocalixto.android.rembrandt.presentation.ui.main

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.shape.MaterialShapeDrawable
import com.listocalixto.android.rembrandt.R
import com.listocalixto.android.rembrandt.databinding.FragmentMainBinding
import com.listocalixto.android.rembrandt.presentation.ui.main.MainFragmentDirections.Companion.actionMainFragmentToDisplayArtworkFragment
import com.listocalixto.android.rembrandt.presentation.ui.main.MainUiEvent.NavigateToDisplayImageFragment
import com.listocalixto.android.rembrandt.presentation.ui.main.detail.artwork.ArtworkDetailFragmentArgs
import com.listocalixto.android.rembrandt.presentation.ui.main.home.HomeFragment
import com.listocalixto.android.rembrandt.presentation.utility.extentions.applyFadeThroughExitTransition
import com.listocalixto.android.rembrandt.presentation.utility.extentions.collectFlowWithLifeCycle
import com.listocalixto.android.rembrandt.presentation.utility.extentions.colorize
import com.listocalixto.android.rembrandt.presentation.utility.extentions.getNavHost
import com.listocalixto.android.rembrandt.presentation.utility.extentions.isDarkMode
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main), NavController.OnDestinationChangedListener {

    private val viewModel by activityViewModels<MainViewModel>()
    private val currentNavigationFragment: Fragment?
        get() = childFragmentManager
            .findFragmentById(R.id.navHostMainFragment)
            ?.childFragmentManager
            ?.fragments
            ?.first()

    private val homeFragment: Fragment?
        get() = currentNavigationFragment as? HomeFragment

    private var binding: FragmentMainBinding? = null
    private var appBarDefaultBackground: Drawable? = null
    private var lastGradientColor: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        binding?.run {
            lifecycleOwner = this@MainFragment.viewLifecycleOwner
            mainViewModel = viewModel
            if (appBarDefaultBackground == null) {
                appBarDefaultBackground = appBar.background
            }
            val navHostFragment = getNavHost(navHostMainFragment.id)
            val navController = navHostFragment.navController
            bottomNav.setupWithNavController(navController)
            navController.addOnDestinationChangedListener(this@MainFragment)
            collectNavigateState()
        }
    }

    private fun collectNavigateState() {
        collectFlowWithLifeCycle(viewModel.navigationState) { state ->
            when (state) {
                is NavigateToDisplayImageFragment -> {
                    val image = state.sharedElement
                    val args = state.args
                    applyFadeThroughExitTransition()
                    val transitionName = getString(R.string.display_artwork_transition_name)
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
        }
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?,
    ) {
        (binding ?: return).run {
            when (destination.id) {
                R.id.exploreFragment -> {
                    extendedFab.show()
                    appBar.background = appBarDefaultBackground
                    homeFragment?.apply {
                        applyFadeThroughExitTransition()
                    }
                }

                R.id.homeFragment -> {
                    appBar.background = appBarDefaultBackground
                    extendedFab.hide()
                }

                R.id.artworkDetailFragment -> {
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
                }

                R.id.favoritesFragment -> {
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
