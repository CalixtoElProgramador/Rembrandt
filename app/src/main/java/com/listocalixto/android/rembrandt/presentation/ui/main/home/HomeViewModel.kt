package com.listocalixto.android.rembrandt.presentation.ui.main.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.listocalixto.android.rembrandt.domain.usecase.main.wrapper.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
            _uiState.update { it.copy(isLoading = true) }
            useCases.getArtworksByPage(1).collect { result ->
                result.onSuccess { data ->
                    val artworks = withContext(Dispatchers.Default) {
                        data.artworks.map { ArtworkUiState.domainToUiState(it) }
                    }
                    _uiState.update { it.copy(artworks = artworks, isLoading = false) }
                }.onFailure {
                    _uiState.update { it.copy(isLoading = false) }
                }
            }
        }
    }

    fun onEvent(event: HomeUiEvent): Unit = when (event) {
        is HomeUiEvent.ObFavorite -> {
            viewModelScope.launch(viewModelDispatcher) {
                val artwork = useCases.getArtworkById(event.artworkId)
                useCases.updateArtwork(artwork.copy(isFavorite = !artwork.isFavorite))
            }
            Unit
        }

        is HomeUiEvent.OnShare -> {}
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}
