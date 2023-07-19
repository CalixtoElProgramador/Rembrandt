package com.listocalixto.android.rembrandt.navigation.principal

import com.listocalixto.android.rembrandt.core.ui.navigation.PrincipalViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class PrincipalViewModelImpl @Inject constructor() : PrincipalViewModel() {

    private val _uiState = MutableStateFlow(PrincipalUiStateImpl())
    override val uiState = _uiState.asStateFlow()

    override fun setLoading(isLoading: Boolean) {
        _uiState.update { it.copy(isLoading = isLoading) }
    }

    fun updateLoading(isLoading: Boolean) {
        _uiState.update { it.copy(isLoading = isLoading) }
    }
}
