package com.listocalixto.android.rembrandt.navigation.principal

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
import com.listocalixto.android.rembrandt.core.ui.navigation.PrincipalFragment
import com.listocalixto.android.rembrandt.databinding.FragmentPrincipalBinding
import com.listocalixto.android.rembrandt.feature.artworkdetail.ArtworkDetailFragmentArgs
import com.listocalixto.android.rembrandt.feature.explore.ExploreFragment
import com.listocalixto.android.rembrandt.feature.favorites.FavoritesFragment
import com.listocalixto.android.rembrandt.feature.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint
import com.listocalixto.android.rembrandt.core.ui.R as Rui

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

    private var binding: FragmentPrincipalBinding? = null
    private var appBarDefaultBackground: Drawable? = null
    private var lastGradientColor: Int? = null

    override val navHostFragmentIdRes: Int = R.id.navHostMainFragment
    override val extendedFabIdRes: Int = R.id.extendedFab
    override val linearProgressIdRes: Int = R.id.linearProgress
    override val appBarIdRes: Int = R.id.appBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPrincipalBinding.bind(view)
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
        extras: FragmentNavigator.Extras,
    ) {
        val direction = ArtworkDetailGraphDirections.showArtworkDetail(
            previousImageMemoryKey = imageMemoryCacheKey,
            previousImageAmbientColor = imageAmbientColor,
            id = artworkId,
            showEnterAnimations = shouldShowEnterAnimations,
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
                else -> appBar.background = appBarDefaultBackground
            }
        }
    }

    private fun FragmentPrincipalBinding.onExploreFragment() {
        extendedFab.show()
        appBar.background = appBarDefaultBackground
        topLevelFragment?.apply {
            applyFadeThroughExitTransition()
        }
    }

    private fun FragmentPrincipalBinding.onHomeFragment() {
        appBar.background = appBarDefaultBackground
        extendedFab.hide()
        topLevelFragment?.apply {
            applyFadeThroughExitTransition()
        }
    }

    private fun FragmentPrincipalBinding.onFavoritesFragment() {
        extendedFab.hide()
        appBar.background = appBarDefaultBackground
        topLevelFragment?.apply {
            applyFadeThroughExitTransition()
        }
    }

    private fun FragmentPrincipalBinding.onArtworkDetailFragment(
        arguments: Bundle?,
    ) {
        extendedFab.apply {
            icon = ResourcesCompat.getDrawable(
                context.resources,
                Rui.drawable.ic_translate,
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
                lastGradientColor = args.previousImageAmbientColor
                appBar.colorize(
                    currentColor = currentColorBackground,
                    newColor = args.previousImageAmbientColor,
                )
            }
        }
    }
}
