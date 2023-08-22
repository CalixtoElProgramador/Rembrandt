package com.listocalixto.android.feature.savetocollection.saveto

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.listocalixto.android.rembrandt.core.ui.adapter.SaveToCollectionAdapter
import com.listocalixto.android.rembrandt.core.ui.extensions.SharedAxisTransitionType.TRANSITION_X
import com.listocalixto.android.rembrandt.core.ui.extensions.applyExitSharedAxisTransition
import com.listocalixto.android.rembrandt.core.ui.extensions.collectFlowWithLifeCycle
import com.listocalixto.android.rembrandt.feature.savetocollection.R
import com.listocalixto.android.rembrandt.feature.savetocollection.databinding.FragmentSaveToCollectionBinding
import dagger.hilt.android.AndroidEntryPoint
import com.listocalixto.android.rembrandt.feature.savetocollection.databinding.FragmentSaveToCollectionBinding as Binding

@AndroidEntryPoint
internal class SaveToCollectionFragment : Fragment(R.layout.fragment_save_to_collection) {
    private val _parentFragment: Fragment
        get() = (parentFragment as NavHostFragment).requireParentFragment()
    private val viewModel: SaveToCollectionViewModel by viewModels({ _parentFragment })
    private var adapter: SaveToCollectionAdapter? = null

    private var binding: Binding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = Binding.bind(view)
        binding?.run {
            setupBinding()
            setupCollections()
            collectUiState()
        }
    }

    private fun FragmentSaveToCollectionBinding.setupCollections() {
        adapter = SaveToCollectionAdapter(
            viewModel::onCheckCollection,
            ::navigateToAddEditCollection,
        ).also {
            collections.adapter = it
        }
    }

    private fun FragmentSaveToCollectionBinding.setupBinding() {
        lifecycleOwner = viewLifecycleOwner
        saveToCollectionFragment = this@SaveToCollectionFragment
        saveToCollectionViewModel = viewModel
    }

    private fun Binding.collectUiState() {
        collectFlowWithLifeCycle(viewModel.uiState) { state ->
            adapter?.submitList(state.collections)
        }
    }

    private fun navigateToAddEditCollection(collectionId: String?) {
        val direction = SaveToCollectionFragmentDirections.toAddEditCollectionFragment(collectionId)
        applyExitSharedAxisTransition(TRANSITION_X)
        findNavController().navigate(direction)
    }

    fun onCreateNewCollectionClick() {
        navigateToAddEditCollection(collectionId = null)
    }

    internal companion object {
        const val ARTWORK_ID_KEY = "artworkId"
    }
}
