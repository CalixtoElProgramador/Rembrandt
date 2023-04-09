package com.listocalixto.android.rembrandt.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val viewModelDispatcher = viewModelScope.coroutineContext + Dispatchers.Main

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    private val _sharedEvent = MutableSharedFlow<MainUiEvent>()
    val sharedFlow = _sharedEvent.asSharedFlow()

    fun sendEvent(event: MainUiEvent) = viewModelScope.launch(viewModelDispatcher) {
        _sharedEvent.emit(event)
        when (event) {
            is MainUiEvent.SetLoading -> {
                _uiState.update { it.copy(isLoading = event.isLoading) }
            }
        }
    }
}
