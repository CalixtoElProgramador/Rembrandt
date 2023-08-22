package com.listocalixto.android.feature.savetocollection.addedit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.listocalixto.android.rembrandt.core.ui.extensions.SharedAxisTransitionType.TRANSITION_X
import com.listocalixto.android.rembrandt.core.ui.extensions.applyEnterSharedAxisTransition
import com.listocalixto.android.rembrandt.feature.savetocollection.R
import com.listocalixto.android.rembrandt.feature.savetocollection.databinding.FragmentAddEditCollectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class AddEditCollectionFragment : Fragment(R.layout.fragment_add_edit_collection) {

    private val viewModel: AddEditCollectionViewModel by viewModels()

    private var binding: FragmentAddEditCollectionBinding? = null
    private var iconButtonBack: MaterialButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyEnterSharedAxisTransition(TRANSITION_X)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddEditCollectionBinding.bind(view)
        binding?.run {
            lifecycleOwner = viewLifecycleOwner
            addEditCollectionFragment = this@AddEditCollectionFragment
            addEditCollectionViewModel = viewModel
        }
    }

    fun onCreateClick() {
        viewModel.onCreateClick()
        findNavController().navigateUp()
    }

    fun onUpdateClick() {
        viewModel.onUpdateClick()
        findNavController().navigateUp()
    }

    fun onDeleteClick() {
        viewModel.onDeleteClick()
        findNavController().navigateUp()
    }

    internal companion object {
        const val COLLECTION_ID_KEY = "collectionId"
    }
}
