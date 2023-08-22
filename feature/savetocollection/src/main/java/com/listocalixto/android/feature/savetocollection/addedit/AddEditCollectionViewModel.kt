package com.listocalixto.android.feature.savetocollection.addedit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.listocalixto.android.feature.savetocollection.addedit.AddEditCollectionFragment.Companion.COLLECTION_ID_KEY
import com.listocalixto.android.rembrandt.common.dependencies.di.Dispatcher
import com.listocalixto.android.rembrandt.common.dependencies.di.RDispatchers.Main
import com.listocalixto.android.rembrandt.core.domain.usecase.CreateCollectionUseCase
import com.listocalixto.android.rembrandt.core.domain.usecase.DeleteCollectionByIdUseCase
import com.listocalixto.android.rembrandt.core.domain.usecase.GetCollectionByIdUseCase
import com.listocalixto.android.rembrandt.core.domain.usecase.UpdateCollectionUseCase
import com.listocalixto.android.rembrandt.core.ui.R.string.no_longer_than_60_characters
import com.listocalixto.android.rembrandt.core.ui.R.string.required
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AddEditCollectionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getCollectionById: GetCollectionByIdUseCase,
    private val createCollection: CreateCollectionUseCase,
    private val updateCollection: UpdateCollectionUseCase,
    private val deleteCollectionById: DeleteCollectionByIdUseCase,
    @Dispatcher(Main) mainDispatcher: CoroutineDispatcher,
) : ViewModel() {

    val name = MutableStateFlow("")
    val description = MutableStateFlow("")
    private val isPrivate = MutableStateFlow(false)
    private val collectionId: String? = savedStateHandle[COLLECTION_ID_KEY]
    private val viewModelDispatcher = viewModelScope.coroutineContext + mainDispatcher
    private val collectionFlow = flow {
        collectionId?.let {
            getCollectionById(collectionId)?.also { collection ->
                emit(collection)
                name.update { collection.name }
                description.update { collection.description }
                isPrivate.update { collection.isPrivate }
            } ?: emit(null)
        } ?: emit(null)
    }

    val uiState: StateFlow<AddEditCollectionUiState> =
        collectionFlow.combine(name) { collection, name ->
            if (collection != null) {
                AddEditCollectionUiState.Edit(
                    collection = collection,
                    shouldEnableErrorNameCollection = !isNameLengthCorrect(name),
                    shouldEnableCreateButton = name.isNotEmpty() && isNameLengthCorrect(name),
                    helperText = getHelperTextAccordingName(name),
                )
            } else {
                getAddCollectionUiState(name)
            }
        }.catch {
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = getAddCollectionUiState(name.value),
        )

    private fun getAddCollectionUiState(name: String) = AddEditCollectionUiState.Add(
        shouldEnableErrorNameCollection = !isNameLengthCorrect(name),
        shouldEnableCreateButton = name.isNotEmpty() && isNameLengthCorrect(name),
        helperText = getHelperTextAccordingName(name),
    )

    private fun getHelperTextAccordingName(name: String): Int {
        return if (isNameLengthCorrect(name)) required else no_longer_than_60_characters
    }

    private fun isNameLengthCorrect(name: String) = name.length <= COLLECTION_NAME_MAX_LENGTH

    fun onCreateClick() {
        val name = name.value
        val description = description.value
        val isPrivate = isPrivate.value
        viewModelScope.launch(viewModelDispatcher) {
            createCollection(
                name,
                description,
                isPrivate,
            )
        }
    }

    fun onUpdateClick() {
        val state = uiState.value
        if (state !is AddEditCollectionUiState.Edit) return
        val name = name.value
        val description = description.value
        val isPrivate = isPrivate.value
        viewModelScope.launch(viewModelDispatcher) {
            updateCollection(
                state.collection.copy(
                    name = name,
                    description = description,
                    isPrivate = isPrivate,
                ),
            )
        }
    }

    fun onDeleteClick() {
        val state = uiState.value
        if (state !is AddEditCollectionUiState.Edit) return
        viewModelScope.launch(viewModelDispatcher) {
            deleteCollectionById(state.collection.id)
        }
    }

    internal companion object {
        const val COLLECTION_NAME_MAX_LENGTH = 60
    }
}
