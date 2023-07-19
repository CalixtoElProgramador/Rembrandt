package com.listocalixto.android.rembrandt.core.ui.navigation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class PrincipalViewModel : ViewModel() {
    abstract val uiState: StateFlow<PrincipalUiState>
    abstract fun setLoading(isLoading: Boolean)
}
