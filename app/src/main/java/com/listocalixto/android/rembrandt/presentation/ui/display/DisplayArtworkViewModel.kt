package com.listocalixto.android.rembrandt.presentation.ui.display

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.listocalixto.android.rembrandt.R
import com.listocalixto.android.rembrandt.presentation.ui.display.DisplayArtworkFragment.Companion.ALT_TEXT_KEY
import com.listocalixto.android.rembrandt.presentation.ui.display.DisplayArtworkFragment.Companion.IMAGE_URL_KEY
import com.listocalixto.android.rembrandt.presentation.ui.display.DisplayArtworkFragment.Companion.MEMORY_CACHE_KEY_ID_KEY
import com.listocalixto.android.rembrandt.presentation.ui.display.DisplayArtworkFragment.Companion.TOUCH_POSITION_X_KEY
import com.listocalixto.android.rembrandt.presentation.ui.display.DisplayArtworkFragment.Companion.TOUCH_POSITION_Y_KEY
import com.listocalixto.android.rembrandt.presentation.ui.display.DisplayArtworkFragment.Companion.ZOOM_KEY
import com.listocalixto.android.rembrandt.core.ui.utility.UiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import com.listocalixto.android.rembrandt.core.ui.R as Rui

class DisplayArtworkViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DisplayArtworkUiState())
    val uiState: StateFlow<DisplayArtworkUiState> = _uiState.asStateFlow()

    init {
        val memoryCacheKey: String? = savedStateHandle[MEMORY_CACHE_KEY_ID_KEY]
        val altText: String? = savedStateHandle[ALT_TEXT_KEY]
        try {
            val imageUrl: String = savedStateHandle[IMAGE_URL_KEY]!!
            val touchPositionX: Float = savedStateHandle[TOUCH_POSITION_X_KEY]!!
            val touchPositionY: Float = savedStateHandle[TOUCH_POSITION_Y_KEY]!!
            val zoom: Float = savedStateHandle[ZOOM_KEY]!!
            _uiState.update {
                it.copy(
                    imageUrl = imageUrl,
                    altText = altText,
                    memoryCacheKey = memoryCacheKey,
                    focusX = touchPositionX,
                    focusY = touchPositionY,
                    scale = zoom,
                )
            }
        } catch (e: NullPointerException) {
            _uiState.update {
                it.copy(
                    errorMessage = UiText.StringResource(
                        value = Rui.string.err_image_not_found,
                    ),
                )
            }
        }
    }

    fun setZoom(scale: Float, focusX: Float, focusY: Float) {
        _uiState.update {
            it.copy(
                scale = scale,
                focusX = focusX,
                focusY = focusY,
                shouldAnimateZoom = true,
            )
        }
    }

    fun onZoomAnimationTriggered() {
        _uiState.update {
            it.copy(shouldAnimateZoom = false)
        }
    }
}
