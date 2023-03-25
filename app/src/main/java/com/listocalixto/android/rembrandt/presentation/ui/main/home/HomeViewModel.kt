package com.listocalixto.android.rembrandt.presentation.ui.main.home

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.listocalixto.android.rembrandt.domain.usecase.main.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useCases: HomeUseCases,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private val viewModelDispatcher = viewModelScope.coroutineContext + Dispatchers.Main

    init {
        viewModelScope.launch(viewModelDispatcher) {
            useCases.getArtworksByPage(1).catch { exception ->
                Log.e(TAG, "exception: $exception ")
            }.collect { result ->
                result.onSuccess { data ->
                    Log.d(TAG, "onSuccess: ${data.artworks}")
                    val artworks = data.artworks.map { ArtworkUiState.domainToUiState(it) }
                    _uiState.update { it.copy(artworks = artworks) }
                }.onFailure {
                    Log.e(TAG, "onFailure: $it")
                }
            }
        }
    }

    fun onEvent(event: HomeUiEvent): Unit = when (event) {
        is HomeUiEvent.ObFavorite -> {}
        is HomeUiEvent.OnShare -> {}
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}
