package com.listocalixto.android.feature.savetocollection.addedit

import androidx.annotation.StringRes
import com.listocalixto.android.rembrandt.common.entities.Collection

internal sealed interface AddEditCollectionUiState {
    val shouldEnableErrorNameCollection: Boolean
    val shouldEnableCreateButton: Boolean
    val shouldDisplayAddCollectionState: Boolean

    @get:StringRes
    val helperText: Int

    data class Add(
        override val shouldEnableErrorNameCollection: Boolean,
        override val shouldEnableCreateButton: Boolean,
        override val helperText: Int,
        override val shouldDisplayAddCollectionState: Boolean = true,
    ) : AddEditCollectionUiState

    data class Edit(
        val collection: Collection,
        override val shouldEnableErrorNameCollection: Boolean,
        override val shouldEnableCreateButton: Boolean,
        override val helperText: Int,
        override val shouldDisplayAddCollectionState: Boolean = false,
    ) : AddEditCollectionUiState
}
