package com.listocalixto.android.rembrandt.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.listocalixto.android.rembrandt.common.dependencies.di.Dispatcher
import com.listocalixto.android.rembrandt.common.dependencies.di.RDispatchers.Default
import com.listocalixto.android.rembrandt.core.domain.usecase.ObserveAllArtworksUserUseCase
import com.listocalixto.android.rembrandt.core.domain.usecase.ToggleFavoriteArtworkByIdUseCase
import com.listocalixto.android.rembrandt.core.domain.utility.ArtworkQuery
import com.listocalixto.android.rembrandt.core.ui.states.ArtworkCarouselUiState
import com.listocalixto.android.rembrandt.core.ui.states.ArtworkCollageUiState
import com.listocalixto.android.rembrandt.core.ui.states.ArtworkUserUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val observeAllArtworksUser: ObserveAllArtworksUserUseCase,
    private val toggleFavoriteArtworkId: ToggleFavoriteArtworkByIdUseCase,
    @Dispatcher(Default) private val defaultDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private val viewModelDispatcher = viewModelScope.coroutineContext + Dispatchers.Main

    init {
        viewModelScope.launch(viewModelDispatcher) {
            _uiState.update { it.copy(isLoading = true) }
            observeAllArtworksUser(ArtworkQuery()).catch {
                // TODO: Manage exceptions
            }.map { artworksUsers ->
                mutableListOf(
                    ArtworkCarouselUiState(artworksUsers.subList(0, 5)),
                    ArtworkUserUiState(artworksUsers[6]),
                    ArtworkUserUiState(artworksUsers[7]),
                    ArtworkUserUiState(artworksUsers[8]),
                    ArtworkUserUiState(artworksUsers[9]),
                    ArtworkCollageUiState(
                        artworksUsers.subList(10, 15),
                        title = "Impressionism Collection",
                        subtitle = "Discover the new paintings we have for you from the impressionist artistic period.",
                    ),
                ).also { list ->
                    list.addAll(
                        artworksUsers.subList(16, artworksUsers.lastIndex).map {
                            ArtworkUserUiState(it)
                        },
                    )
                }
            }.flowOn(defaultDispatcher).collect { homeItems ->
                _uiState.update { it.copy(items = homeItems, isLoading = false) }
            }
        }
    }

    fun onFavoriteClick(artworkId: Long, isFavorite: Boolean) {
        viewModelScope.launch(viewModelDispatcher) {
            toggleFavoriteArtworkId(artworkId, isFavorite)
        }
    }
}
