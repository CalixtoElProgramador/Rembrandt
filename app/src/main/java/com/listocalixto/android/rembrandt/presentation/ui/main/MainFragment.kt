package com.listocalixto.android.rembrandt.presentation.ui.main

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.listocalixto.android.rembrandt.R
import com.listocalixto.android.rembrandt.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment :
    Fragment(R.layout.fragment_main),
    NavController.OnDestinationChangedListener {

    private val viewModel by activityViewModels<MainViewModel>()

    private var binding: FragmentMainBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        binding?.run {
            lifecycleOwner = this@MainFragment.viewLifecycleOwner
            mainViewModel = viewModel

            val navHostFragment =
                childFragmentManager.findFragmentById(navHostMainFragment.id) as NavHostFragment
            val navController = navHostFragment.navController
            bottomNav.setupWithNavController(navController)
            navController.addOnDestinationChangedListener(this@MainFragment)
        }
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        (binding ?: return).run {
            when (destination.id) {
                R.id.exploreFragment -> {
                    extendedFab.show()
                }
                R.id.homeFragment -> {
                    extendedFab.extend()
                    extendedFab.hide()
                }
                R.id.artworkDetailFragment -> {
                    extendedFab.apply {
                        show()
                        text =
                            context.resources.getText(
                                R.string.frag_artwork_detail_extended_fab_translate
                            )
                        icon = ResourcesCompat.getDrawable(
                            context.resources,
                            R.drawable.ic_translate,
                            activity?.theme
                        )
                    }
                }
                R.id.favoritesFragment -> {
                    extendedFab.hide()
                }
                else -> {}
            }
        }
    }

    companion object {
        const val TAG = "MainFragment"
    }
}
