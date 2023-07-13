package com.listocalixto.android.rembrandt.presentation.ui.main

import android.view.View
import com.listocalixto.android.rembrandt.presentation.ui.display.DisplayArtworkFragmentArgs

sealed interface MainUiEvent {
    class NavigateToDisplayImageFragment(
        val args: DisplayArtworkFragmentArgs,
        val sharedElement: View,
    ) : MainUiEvent
}
