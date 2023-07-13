package com.listocalixto.android.rembrandt.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _navigationState = MutableSharedFlow<MainUiEvent>(replay = 0)
    val navigationState = _navigationState.asSharedFlow()

    fun sendEvent(event: MainUiEvent) {
        viewModelScope.launch { _navigationState.emit(event) }
    }
}
