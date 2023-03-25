package com.listocalixto.android.rembrandt.presentation.ui.main.home

import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.listocalixto.android.rembrandt.R
import com.listocalixto.android.rembrandt.presentation.adapter.ArtworkAdapter
import com.listocalixto.android.rembrandt.presentation.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.listocalixto.android.rembrandt.databinding.FragmentHomeBinding as Binding

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private val sharedViewModel: MainViewModel by hiltNavGraphViewModels(R.id.main_module_graph)

    private var adapter: ArtworkAdapter? = null
    private var binding: Binding? = null
    private var linearProgress: LinearProgressIndicator? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = Binding.bind(view)
        binding?.run {
            setupBinding()
            setupRecyclerView()
            initExteriorViews()
            collectUiState()
        }
    }

    private fun Binding.setupBinding() {
        lifecycleOwner = this@HomeFragment.viewLifecycleOwner
        homeViewModel = viewModel
    }

    private fun Binding.setupRecyclerView() {
        adapter = ArtworkAdapter(viewModel::onEvent).also {
            listArtworks.adapter = it
        }
    }

    private fun initExteriorViews() {
        linearProgress = activity?.findViewById(R.id.linearProgress)
    }

    private fun collectUiState() = viewLifecycleOwner.lifecycleScope.launch {
        viewModel.uiState.flowWithLifecycle(lifecycle).collect { state ->
            adapter?.submitList(state.artworks)
            linearProgress?.visibility = if (state.isLoading) VISIBLE else INVISIBLE
        }
    }
}
