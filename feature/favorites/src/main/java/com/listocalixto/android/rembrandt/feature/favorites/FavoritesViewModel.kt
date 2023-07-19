package com.listocalixto.android.rembrandt.feature.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.listocalixto.android.rembrandt.common.dependencies.di.Dispatcher
import com.listocalixto.android.rembrandt.common.dependencies.di.RDispatchers.Default
import com.listocalixto.android.rembrandt.common.dependencies.di.RDispatchers.Main
import com.listocalixto.android.rembrandt.core.domain.usecase.ObserveAllFavoriteArtworksUseCase
import com.listocalixto.android.rembrandt.core.domain.usecase.ToggleFavoriteArtworkIdUseCase
import com.listocalixto.android.rembrandt.core.ui.states.ArtworkUserUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class FavoritesViewModel @Inject constructor(
    private val toggleFavoriteArtworkId: ToggleFavoriteArtworkIdUseCase,
    observeAllFavoriteArtwork: ObserveAllFavoriteArtworksUseCase,
    @Dispatcher(Default) defaultDispatcher: CoroutineDispatcher,
    @Dispatcher(Main) mainDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoritesUiState())
    val uiState = _uiState.asStateFlow()

    private val viewModelDispatcher = viewModelScope.coroutineContext + mainDispatcher

    init {
        viewModelScope.launch(viewModelDispatcher) {
            observeAllFavoriteArtwork().catch {
            }.map { artworksUser ->
                artworksUser.map { ArtworkUserUiState(it) }
            }.flowOn(defaultDispatcher).collect { artworksUiState ->
                _uiState.update { it.copy(artworks = artworksUiState) }
            }
        }
    }

    fun onFavoriteClick(artworkId: Long, isFavorite: Boolean) {
        viewModelScope.launch(viewModelDispatcher) {
            toggleFavoriteArtworkId(artworkId, isFavorite)
        }
    }
}
