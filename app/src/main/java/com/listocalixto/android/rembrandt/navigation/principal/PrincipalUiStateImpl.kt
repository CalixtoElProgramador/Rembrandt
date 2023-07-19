package com.listocalixto.android.rembrandt.navigation.principal

import com.listocalixto.android.rembrandt.core.ui.navigation.PrincipalUiState

internal data class PrincipalUiStateImpl(
    override val isLoading: Boolean = true,
) : PrincipalUiState
