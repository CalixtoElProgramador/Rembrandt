package com.listocalixto.android.rembrandt.presentation.ui.main.home

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.listocalixto.android.rembrandt.domain.usecase.main.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useCases: HomeUseCases,
) : ViewModel() {

    private val viewModelDispatcher = viewModelScope.coroutineContext + Dispatchers.Main

    init {
        viewModelScope.launch(viewModelDispatcher) {
            useCases.getArtworksByPage(1).catch { exception ->
                Log.e(TAG, "exception: $exception ")
            }.collect { result ->
                result.onSuccess { data ->
                    Log.d(TAG, "onSuccess: ${data.artworks}")
                }.onFailure {
                    Log.e(TAG, "onFailure: $it")
                }
            }
        }
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}
