package com.listocalixto.android.rembrandt.navigation.principal

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.shape.MaterialShapeDrawable
import com.listocalixto.android.rembrandt.ArtworkDetailGraphDirections
import com.listocalixto.android.rembrandt.DisplayImageGraphDirections
import com.listocalixto.android.rembrandt.R
import com.listocalixto.android.rembrandt.core.ui.extensions.applyFadeThroughExitTransition
import com.listocalixto.android.rembrandt.core.ui.extensions.colorize
import com.listocalixto.android.rembrandt.core.ui.extensions.getNavHost
import com.listocalixto.android.rembrandt.core.ui.extensions.gone
import com.listocalixto.android.rembrandt.core.ui.extensions.isDarkMode
import com.listocalixto.android.rembrandt.core.ui.navigation.BottomNavTabType
import com.listocalixto.android.rembrandt.core.ui.navigation.PrincipalFragment
import com.listocalixto.android.rembrandt.feature.artworkdetail.ArtworkDetailFragmentArgs
import com.listocalixto.android.rembrandt.feature.explore.ExploreFragment
import com.listocalixto.android.rembrandt.feature.favorites.FavoritesFragment
import com.listocalixto.android.rembrandt.feature.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint
import com.listocalixto.android.rembrandt.databinding.FragmentPrincipalBinding as Binding

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

    private val topLevelFragment: Fragment?
        get() = currentNavigationFragment as? HomeFragment
            ?: currentNavigationFragment as? ExploreFragment
            ?: currentNavigationFragment as? FavoritesFragment

    private var binding: Binding? = null
    private var appBarDefaultBackground: Drawable? = null
    private var lastGradientColor: Int? = null

    override val navHostFragmentIdRes: Int = R.id.navHostMainFragment
    override val extendedFabIdRes: Int = R.id.extendedFab
    override val smallFabIdRes: Int = R.id.smallFab
    override val linearProgressIdRes: Int = R.id.linearProgress
    override val appBarIdRes: Int = R.id.appBar
    override val containerFABs: Int = R.id.containerFABs

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = Binding.bind(view)
        binding?.run {
            lifecycleOwner = this@PrincipalFragmentImpl.viewLifecycleOwner
            mainViewModel = viewModel
            if (appBarDefaultBackground == null) {
                appBarDefaultBackground = appBar.background
            }
            linearProgress.gone()
            val navHostFragment = getNavHost(navHostMainFragment.id)
            val navController = navHostFragment.navController
            bottomNav.setupWithNavController(navController)
            navController.addOnDestinationChangedListener(this@PrincipalFragmentImpl)
        }
    }

    override fun navigateToArtworkDetail(
        artworkId: Long,
        imageMemoryCacheKey: String?,
        shouldShowEnterAnimations: Boolean,
        imageAmbientColor: Int,
        comesFrom: BottomNavTabType,
        extras: FragmentNavigator.Extras,
    ) {
        val direction = ArtworkDetailGraphDirections.showArtworkDetail(
            previousImageMemoryKey = imageMemoryCacheKey,
            previousImageAmbientColor = imageAmbientColor,
            id = artworkId,
            showEnterAnimations = shouldShowEnterAnimations,
            comesFrom = comesFrom,
        )
        currentNavigationFragment?.findNavController()?.navigate(direction, extras)
    }

    override fun navigateToDisplayImage(
        imageUrl: String,
        alternativeText: String,
        previousImageMemoryCacheKey: String,
        touchPositionX: Float,
        touchPositionY: Float,
        zoom: Float,
        extras: FragmentNavigator.Extras,
    ) {
        val direction = DisplayImageGraphDirections.showDisplayImage(
            imageUrl,
            alternativeText,
            previousImageMemoryCacheKey,
            touchPositionX,
            touchPositionY,
            zoom,
        )
        applyFadeThroughExitTransition()
        findNavController().navigate(direction, extras)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?,
    ) {
        (binding ?: return).run {
            when (destination.id) {
                R.id.explore_graph -> onExploreFragment()
                R.id.homeFragment -> onHomeFragment()
                R.id.artworkDetailFragment -> onArtworkDetailFragment(arguments)
                R.id.favoritesFragment -> onFavoritesFragment()
                else -> {
                    appBar.background = appBarDefaultBackground
                }
            }
        }
    }

    private fun Binding.onExploreFragment() {
        extendedFab.hide()
        smallFab.hide()
        updateIconSelected(BottomNavTabType.Explore)
        appBar.background = appBarDefaultBackground
        topLevelFragment?.apply {
            applyFadeThroughExitTransition()
        }
    }

    private fun Binding.updateIconSelected(tabSelected: BottomNavTabType) {
        val theme = activity?.theme
        BottomNavTabType.values().forEach { tab ->
            val menuItem = bottomNav.menu.getItem(tab.ordinal)
            menuItem.icon = tab.getIcon(resources, theme, isSelected = tab == tabSelected)
        }
    }

    private fun Binding.onHomeFragment() {
        appBar.background = appBarDefaultBackground
        updateIconSelected(BottomNavTabType.Home)
        extendedFab.hide()
        smallFab.hide()
        topLevelFragment?.apply {
            applyFadeThroughExitTransition()
        }
    }

    private fun Binding.onFavoritesFragment() {
        extendedFab.hide()
        smallFab.hide()
        updateIconSelected(BottomNavTabType.Favorites)
        appBar.background = appBarDefaultBackground
        topLevelFragment?.apply {
            applyFadeThroughExitTransition()
        }
    }

    private fun Binding.onArtworkDetailFragment(
        arguments: Bundle?,
    ) {
        bottomNav.setOnItemReselectedListener {}
        arguments?.let {
            val args = ArtworkDetailFragmentArgs.fromBundle(arguments)
            updateIconSelected(args.comesFrom)
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
                lastGradientColor = args.previousImageAmbientColor
                appBar.colorize(
                    currentColor = currentColorBackground,
                    newColor = args.previousImageAmbientColor,
                )
            }
        }
    }
}
