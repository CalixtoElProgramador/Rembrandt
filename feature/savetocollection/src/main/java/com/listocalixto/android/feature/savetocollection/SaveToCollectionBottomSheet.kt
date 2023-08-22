package com.listocalixto.android.feature.savetocollection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.listocalixto.android.feature.savetocollection.saveto.SaveToCollectionViewModel
import com.listocalixto.android.rembrandt.core.ui.extensions.getNavHost
import com.listocalixto.android.rembrandt.core.ui.extensions.invisible
import com.listocalixto.android.rembrandt.core.ui.extensions.visible
import com.listocalixto.android.rembrandt.feature.savetocollection.R
import com.listocalixto.android.rembrandt.feature.savetocollection.databinding.BottomSheetSaveToCollectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SaveToCollectionBottomSheet :
    BottomSheetDialogFragment(R.layout.bottom_sheet_save_to_collection),
    NavController.OnDestinationChangedListener {

    var binding: BottomSheetSaveToCollectionBinding? = null
    private val viewModel: SaveToCollectionViewModel by viewModels()
    private val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            // Do something for new state.
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {
            // Do something for slide offset.
        }
    }
    private val currentNavigationFragment: Fragment?
        get() = childFragmentManager
            .findFragmentById(R.id.bottomSheetContainer)
            ?.childFragmentManager
            ?.fragments
            ?.first()
    private var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        bottomSheetBehavior = (dialog as BottomSheetDialog).behavior
        bottomSheetBehavior?.apply {
            saveFlags = BottomSheetBehavior.SAVE_ALL
            state = BottomSheetBehavior.STATE_COLLAPSED
            addBottomSheetCallback(bottomSheetCallback)
        }
        binding = BottomSheetSaveToCollectionBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel
        binding?.run {
            iconButtonBack.setOnClickListener {
                currentNavigationFragment?.findNavController()?.navigateUp()
            }
            val navHostFragment = getNavHost(bottomSheetContainer.id)
            val navController = navHostFragment.navController
            navController.addOnDestinationChangedListener(this@SaveToCollectionBottomSheet)
        }
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?,
    ) {
        (binding ?: return).apply {
            when (destination.id) {
                R.id.saveToCollectionFragment -> {
                    iconButtonBack.invisible()
                }

                R.id.addEditCollectionFragment -> {
                    iconButtonBack.visible()
                }
            }
        }
    }

    override fun onDestroy() {
        bottomSheetBehavior?.removeBottomSheetCallback(bottomSheetCallback)
        super.onDestroy()
    }
}
