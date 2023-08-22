package com.listocalixto.android.feature.savetocollection.saveto

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.listocalixto.android.feature.savetocollection.saveto.SaveToCollectionFragment.Companion.ARTWORK_ID_KEY
import com.listocalixto.android.rembrandt.common.dependencies.di.Dispatcher
import com.listocalixto.android.rembrandt.common.dependencies.di.RDispatchers
import com.listocalixto.android.rembrandt.core.domain.usecase.ObserveAllCollectionsUseCase
import com.listocalixto.android.rembrandt.core.domain.usecase.ToggleArtworkIdToCollectionUseCase
import com.listocalixto.android.rembrandt.core.ui.states.CollectionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SaveToCollectionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    observeAllCollections: ObserveAllCollectionsUseCase,
    private val toggleArtworkIdToCollection: ToggleArtworkIdToCollectionUseCase,
    @Dispatcher(RDispatchers.Main) mainDispatcher: CoroutineDispatcher,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SaveToCollectionUiState())
    private val viewModelDispatcher = viewModelScope.coroutineContext + mainDispatcher
    private val artworkId: Long = savedStateHandle[ARTWORK_ID_KEY]!!
    val uiState: StateFlow<SaveToCollectionUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch(viewModelDispatcher) {
            observeAllCollections().catch {
            }.map { collections ->
                collections.map { CollectionUiState(it, artworkId) }
            }.collect { collectionsUiState ->
                val collections = collectionsUiState.toMutableList()
                val isEditModeActivated = _uiState.value.isEditModeActivated
                collections.replaceAll { collection ->
                    collection.copy(isEditModeActivated = isEditModeActivated)
                }
                _uiState.update { it.copy(artworkId = artworkId, collections = collections) }
            }
        }
    }

    fun toggleEditMode() {
        val collections = uiState.value.collections.toMutableList()
        val toggleEditMode = !_uiState.value.isEditModeActivated
        collections.replaceAll { collection ->
            collection.copy(isEditModeActivated = toggleEditMode)
        }
        _uiState.update {
            it.copy(
                collections = collections.toList(),
                isEditModeActivated = toggleEditMode,
            )
        }
    }

    fun onCheckCollection(item: CollectionUiState) {
        viewModelScope.launch(viewModelDispatcher) {
            val collection = item.toEntity()
            toggleArtworkIdToCollection(artworkId, collection)
        }
    }
}
