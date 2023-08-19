package com.listocalixto.android.rembrandt.feature.artworkdetail

import android.graphics.Color.TRANSPARENT
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.listocalixto.android.rembrandt.common.dependencies.di.Dispatcher
import com.listocalixto.android.rembrandt.common.dependencies.di.RDispatchers.Default
import com.listocalixto.android.rembrandt.common.entities.Translation
import com.listocalixto.android.rembrandt.core.domain.usecase.GetImageUrlUseCase
import com.listocalixto.android.rembrandt.core.domain.usecase.GetKeysAndTranslationRequestsForArtworkUseCase
import com.listocalixto.android.rembrandt.core.domain.usecase.GetManifestByArtworkIdUseCase
import com.listocalixto.android.rembrandt.core.domain.usecase.GetRecommendedArtworksByArtworkIdkUseCase
import com.listocalixto.android.rembrandt.core.domain.usecase.GetTranslationByArtworkIdUseCase
import com.listocalixto.android.rembrandt.core.domain.usecase.GetTranslationsUseCase
import com.listocalixto.android.rembrandt.core.domain.usecase.ObserveArtworkUserByIdUseCase
import com.listocalixto.android.rembrandt.core.domain.usecase.ToggleFavoriteArtworkByIdUseCase
import com.listocalixto.android.rembrandt.core.domain.utility.QualityImageType.ExtraHigh
import com.listocalixto.android.rembrandt.core.domain.utility.TranslationFromType.Artwork
import com.listocalixto.android.rembrandt.core.ui.R.string.frag_artwork_detail_err_translation_not_available
import com.listocalixto.android.rembrandt.core.ui.navigation.BottomNavTabType
import com.listocalixto.android.rembrandt.core.ui.navigation.BottomNavTabType.Home
import com.listocalixto.android.rembrandt.core.ui.states.RecommendedArtworksUiState
import com.listocalixto.android.rembrandt.core.ui.utility.UiText
import com.listocalixto.android.rembrandt.feature.artworkdetail.ArtworkDetailFragment.Companion.AMBIENT_COLOR_KEY
import com.listocalixto.android.rembrandt.feature.artworkdetail.ArtworkDetailFragment.Companion.ARTWORK_ID_KEY
import com.listocalixto.android.rembrandt.feature.artworkdetail.ArtworkDetailFragment.Companion.COMES_FROM_KEY
import com.listocalixto.android.rembrandt.feature.artworkdetail.ArtworkDetailFragment.Companion.MEMORY_CACHE_KEY
import com.listocalixto.android.rembrandt.feature.artworkdetail.ArtworkDetailFragment.Companion.SHOW_ENTER_ANIMATIONS
import com.listocalixto.android.rembrandt.feature.artworkdetail.content.page.ArtworkCharacteristicsFragment
import com.listocalixto.android.rembrandt.feature.artworkdetail.content.page.ArtworkDescriptionFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ArtworkDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    observeArtworkUserById: ObserveArtworkUserByIdUseCase,
    getManifestByArtworkId: GetManifestByArtworkIdUseCase,
    getRecommendedArtworksByArtworkId: GetRecommendedArtworksByArtworkIdkUseCase,
    getTranslationByArtworkId: GetTranslationByArtworkIdUseCase,
    getImageUrl: GetImageUrlUseCase,
    private val toggleFavoriteArtworkById: ToggleFavoriteArtworkByIdUseCase,
    private val getKeysAndTranslationRequestsForArtwork: GetKeysAndTranslationRequestsForArtworkUseCase,
    private val getTranslations: GetTranslationsUseCase,
    @Dispatcher(Default) private val defaultDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ArtworkDetailUiState())
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ArtworkDetailUiState(),
    )
    private val viewModelDispatcher = viewModelScope.coroutineContext + Dispatchers.Main
    val contentFragments = listOf(ArtworkDescriptionFragment(), ArtworkCharacteristicsFragment())

    private var translateJob: Job? = null

    private data class ArtworkDetailFragmentArgs(
        val artworkId: Long,
        val imageMemoryCacheKey: String?,
        val shouldShowEnterAnimations: Boolean,
        val imageAmbientColor: Int,
        val comesFrom: BottomNavTabType,
    )

    init {
        getNavigationArguments(savedStateHandle).run {
            setArgumentsInTheUiState(this)
            getArtworkDetailData(
                artworkId,
                observeArtworkUserById,
                getManifestByArtworkId,
                getRecommendedArtworksByArtworkId,
                getTranslationByArtworkId,
                getImageUrl,
            )
        }
    }

    private fun getArtworkDetailData(
        artworkId: Long,
        observeArtworkUserById: ObserveArtworkUserByIdUseCase,
        getManifestByArtworkId: GetManifestByArtworkIdUseCase,
        getRecommendedArtworksByArtworkId: GetRecommendedArtworksByArtworkIdkUseCase,
        getTranslationByArtworkId: GetTranslationByArtworkIdUseCase,
        getImageUrl: GetImageUrlUseCase,
    ) {
        viewModelScope.launch(viewModelDispatcher) {
            launch {
                observeArtworkUserById(artworkId).catch { throwable ->
                    _uiState.update {
                        it.copy(errorMessage = UiText.StringValue(throwable.message.orEmpty()))
                    }
                }.onEach { artworkUser ->
                    _uiState.update {
                        it.copy(
                            artworkUser = artworkUser,
                            extraHighDefinitionImageUrl = getImageUrl(
                                artworkUser.imageId,
                                ExtraHigh,
                            ),
                        )
                    }
                }.launchIn(this)
            }

            launch {
                flow {
                    emit(getManifestByArtworkId(artworkId))
                }.catch { throwable ->
                    _uiState.update {
                        it.copy(errorMessage = UiText.StringValue(throwable.message.orEmpty()))
                    }
                }.onEach { manifest ->
                    _uiState.update { it.copy(manifest = manifest) }
                }.launchIn(this)
            }

            launch {
                flow {
                    emit(getRecommendedArtworksByArtworkId(artworkId))
                }.flowOn(defaultDispatcher).catch { throwable ->
                    _uiState.update {
                        it.copy(errorMessage = UiText.StringValue(throwable.message.orEmpty()))
                    }
                }.map {
                    it.map { (artwork, recommendationType) ->
                        RecommendedArtworksUiState(artwork, recommendationType)
                    }
                }.onEach { artworkRecommendedUiStates ->
                    _uiState.update { it.copy(recommendedArtworks = artworkRecommendedUiStates) }
                }.launchIn(this)
            }

            launch {
                flow {
                    emit(getTranslationByArtworkId(artworkId))
                }.catch { throwable ->
                    catchTranslationException(throwable)
                }.onEach { translation ->
                    _uiState.update {
                        it.copy(
                            translation = translation,
                            shouldShowOriginalLanguage = translation == null,
                        )
                    }
                }.launchIn(this)
            }
        }
    }

    private fun catchTranslationException(throwable: Throwable) {
        if (throwable is Translation.TargetLanguageNotAvailableException) {
            val errorMessageRes = frag_artwork_detail_err_translation_not_available
            val errorMessageUiText = UiText.StringResource(errorMessageRes)
            _uiState.update { it.copy(errorMessage = errorMessageUiText) }
        } else {
            _uiState.update {
                it.copy(errorMessage = UiText.StringValue(throwable.message.orEmpty()))
            }
        }
    }

    fun onTranslateClick() {
        val isTranslationAvailable = _uiState.value.isTranslationAvailable
        if (isTranslationAvailable) {
            viewModelScope.launch {
                _uiState.update { it.copy(triggerTranslationAnimation = Unit) }
                delay(200)
                _uiState.update {
                    it.copy(
                        shouldShowOriginalLanguage = !it.shouldShowOriginalLanguage,
                        triggerTranslationAnimation = null,
                    )
                }
            }
        } else {
            requestTranslations()
        }
    }

    private fun requestTranslations() {
        if (translateJob != null) return
        val keysAndTranslationRequests = _uiState.value.run {
            getKeysAndTranslationRequestsForArtwork(
                category = category,
                title = title,
                artistDisplay = artistDisplay,
                description = description,
                mediumDisplay = mediumDisplay,
                artworkTypeTitle = artworkTypeTitle,
                placeOfOrigin = placeOfOrigin,
                creditLine = creditLine,
                physicalDimensions = dimensions,
                artistTitle = artistTitle,
                dateDisplay = dateDisplay,
                inscriptions = inscriptions,
                styleTitle = styleTitle,
            )
        }
        val artworkId = _uiState.value.artworkId
        translateJob = viewModelScope.launch(viewModelDispatcher) {
            _uiState.update { it.copy(isLoadingTranslation = true) }
            flow {
                emit(getTranslations(artworkId, fromType = Artwork, keysAndTranslationRequests))
            }.catch { throwable ->
                _uiState.update {
                    it.copy(
                        errorMessage = UiText.StringValue(throwable.message.orEmpty()),
                    )
                }
            }.onEach { translation ->
                _uiState.update { it.copy(triggerTranslationAnimation = Unit) }
                delay(200)
                _uiState.update {
                    it.copy(
                        translation = translation,
                        shouldShowOriginalLanguage = false,
                        isLoadingTranslation = false,
                        triggerTranslationAnimation = null,
                    )
                }
                translateJob = null
            }.launchIn(this)
        }
    }

    private fun setArgumentsInTheUiState(args: ArtworkDetailFragmentArgs) {
        _uiState.update {
            it.copy(
                imageMemoryCacheKey = args.imageMemoryCacheKey,
                imageAmbientColor = args.imageAmbientColor,
                artworkId = args.artworkId,
                triggerEnterAnimations = if (args.shouldShowEnterAnimations) Unit else null,
                wereEnterAnimationsShown = !args.shouldShowEnterAnimations,
                comesFrom = args.comesFrom,
            )
        }
    }

    fun toggleFavorite() {
        val artworkId = _uiState.value.artworkId
        val isFavorite = _uiState.value.isFavorite
        viewModelScope.launch(viewModelDispatcher) {
            toggleFavoriteArtworkById(
                artworkId,
                isFavorite,
            )
        }
    }

    private fun getNavigationArguments(savedStateHandle: SavedStateHandle): ArtworkDetailFragmentArgs {
        val artworkId: Long = savedStateHandle[ARTWORK_ID_KEY] ?: -1
        val imageMemoryCacheKey: String? = savedStateHandle[MEMORY_CACHE_KEY]
        val shouldShowEnterAnimations: Boolean =
            savedStateHandle[SHOW_ENTER_ANIMATIONS] ?: false
        val imageAmbientColor: Int = savedStateHandle[AMBIENT_COLOR_KEY] ?: TRANSPARENT
        val comesFrom: BottomNavTabType = savedStateHandle[COMES_FROM_KEY] ?: Home
        return ArtworkDetailFragmentArgs(
            artworkId = artworkId,
            imageMemoryCacheKey = imageMemoryCacheKey,
            shouldShowEnterAnimations = shouldShowEnterAnimations,
            imageAmbientColor = imageAmbientColor,
            comesFrom = comesFrom,
        )
    }

    fun enterAnimationsTriggered() {
        _uiState.update {
            it.copy(
                triggerEnterAnimations = null,
                wereEnterAnimationsShown = true,
            )
        }
    }

    fun shouldShowTranslatorButton(): Boolean {
        return Locale.getDefault().language != Locale.ENGLISH.language
    }
}
