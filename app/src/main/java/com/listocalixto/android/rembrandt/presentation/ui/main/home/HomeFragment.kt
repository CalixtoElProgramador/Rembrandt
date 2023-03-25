package com.listocalixto.android.rembrandt.presentation.ui.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.listocalixto.android.rembrandt.R
import com.listocalixto.android.rembrandt.presentation.adapter.ArtworkAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.listocalixto.android.rembrandt.databinding.FragmentHomeBinding as Binding

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by hiltNavGraphViewModels(R.id.main_module_graph)

    private var adapter: ArtworkAdapter? = null
    private var binding: Binding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = Binding.bind(view)
        binding?.run {
            lifecycleOwner = this@HomeFragment.viewLifecycleOwner
            homeViewModel = viewModel
            adapter = ArtworkAdapter(viewModel::onEvent).also {
                listArtworks.adapter = it
            }
            collectUiState()
        }
    }

    private fun Binding.collectUiState() = viewLifecycleOwner.lifecycleScope.launch {
        viewModel.uiState.flowWithLifecycle(lifecycle).collect { state ->
            adapter?.submitList(state.artworks)
        }
    }
}
