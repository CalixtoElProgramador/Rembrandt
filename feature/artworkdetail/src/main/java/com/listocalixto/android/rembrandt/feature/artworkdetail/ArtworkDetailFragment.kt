package com.listocalixto.android.rembrandt.feature.artworkdetail

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.res.Resources
import android.graphics.PointF
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import coil.load
import com.google.android.material.R.attr.motionEasingEmphasizedDecelerateInterpolator
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.listocalixto.android.rembrandt.core.ui.adapter.ArtworkRecommendedAdapter
import com.listocalixto.android.rembrandt.core.ui.extensions.applyContainerTransformEnterTransition
import com.listocalixto.android.rembrandt.core.ui.extensions.applyFadeThroughEnterTransition
import com.listocalixto.android.rembrandt.core.ui.extensions.applyFadeThroughExitTransition
import com.listocalixto.android.rembrandt.core.ui.extensions.collectFlowWithLifeCycle
import com.listocalixto.android.rembrandt.core.ui.extensions.getInterpolator
import com.listocalixto.android.rembrandt.core.ui.extensions.isDarkMode
import com.listocalixto.android.rembrandt.core.ui.extensions.startTransition
import com.listocalixto.android.rembrandt.core.ui.navigation.PrincipalFragment
import com.listocalixto.android.rembrandt.core.ui.utility.ColorContainerType
import com.listocalixto.android.rembrandt.presentation.utility.extentions.adjustSizeAccordingScroll
import com.listocalixto.android.rembrandt.presentation.utility.extentions.gone
import com.listocalixto.android.rembrandt.presentation.utility.extentions.visible
import com.ortiz.touchview.OnTouchCoordinatesListener
import dagger.hilt.android.AndroidEntryPoint
import com.listocalixto.android.rembrandt.core.ui.R as Rui
import com.listocalixto.android.rembrandt.feature.artworkdetail.databinding.FragmentArtworkDetailBinding as Binding

@AndroidEntryPoint
class ArtworkDetailFragment : Fragment(R.layout.fragment_artwork_detail) {

    private val viewModel: ArtworkDetailViewModel by viewModels()
    private val artworkRecommendedAdapter =
        ArtworkRecommendedAdapter { artworkId, memoryCacheKey, gradientColor ->
            navigateToSelf(artworkId, memoryCacheKey, gradientColor)
        }
    private val chips = mutableListOf<Chip>()
    private val principalFragment: PrincipalFragment?
        get() = ((parentFragment as? NavHostFragment)?.parentFragment as? PrincipalFragment)

    private var binding: Binding? = null
    private var extendedFab: ExtendedFloatingActionButton? = null
    private var linearProgressIndicator: LinearProgressIndicator? = null
    private var appBar: AppBarLayout? = null
    private var cacheKey: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val colorSurface = com.google.android.material.R.attr.colorSurface
        applyFadeThroughEnterTransition()
        principalFragment?.let {
            applyContainerTransformEnterTransition(
                drawingViewIdRes = it.navHostFragmentId,
                colorContainerType = ColorContainerType.AllContainerColors(colorSurface),
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        binding = Binding.bind(view)
        binding?.run {
            setupInternalViews()
            initExternalViews()
            setupExtendedFab()
            groupTheChips()
            collectUiStateWithBinding()
        }
    }

    private fun Binding.groupTheChips() {
        chips.addAll(
            listOf(
                chipZoomIn,
                chipFavorite,
                chipDownloadImage,
                chipAddCollection,
                chipShare,
            ),
        )
        chips.toList()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun Binding.setupExtendedFab() {
        extendedFab?.adjustSizeAccordingScroll(scrollContainer)
        extendedFab?.setOnClickListener { requestTranslation() }
    }

    private fun Binding.setupInternalViews() {
        lifecycleOwner = this@ArtworkDetailFragment.viewLifecycleOwner
        artworkDetailViewModel = viewModel
        // listRecommended.adapter = artworkRecommendedAdapter
        artworkDetailFragment = this@ArtworkDetailFragment
        image.setOnTouchCoordinatesListener(object : OnTouchCoordinatesListener {
            override fun onTouchCoordinate(view: View, event: MotionEvent, bitmapPoint: PointF) {
                if (event.action == MotionEvent.ACTION_UP) {
                    navigateToDisplayArtwork(
                        touchPositionX = bitmapPoint.x / image.drawable.intrinsicWidth,
                        touchPositionY = bitmapPoint.y / image.drawable.intrinsicHeight,
                        zoom = 3.0f,
                    )
                }
            }
        })
        if (isDarkMode()) {
            gradientView.visible()
        } else {
            gradientView.gone()
        }
    }

    private fun initExternalViews() {
        /*extendedFab = activity?.findViewById(R.id.extendedFab)
        linearProgressIndicator = activity?.findViewById(R.id.linearProgress)
        appBar = activity?.findViewById(R.id.appBar)*/
    }

    private fun Binding.requestTranslation() {
        /*val isTranslationLoading = viewModel.uiState.value.loadingTranslation
        val stringRes = Rui.string.frag_artwork_detail_wait_until_translation_is_ready
        if (isTranslationLoading) {
            val snackbarMessage = UiText.StringResource(stringRes)
            showSnackbar(root, snackbarMessage, duration = SHORT, anchorView = extendedFab)
        } else {
            viewModel.onEvent(TranslateContent)
        }*/
    }

    private fun navigateToSelf(artworkId: Long, memoryCacheKey: String?, gradientColor: Int) {
        applyFadeThroughExitTransition()
        /*val directions = ArtworkDetailFragmentDirections.actionArtworkDetailFragmentSelf(
            gradientColor = gradientColor,
            artworkId = artworkId,
            memoryCacheKey = memoryCacheKey,
            displayInitialAnimations = false,
        )
        findNavController().navigate(directions)*/
    }

    fun navigateToDisplayArtwork(touchPositionX: Float, touchPositionY: Float, zoom: Float) {
        cacheKey?.let {
            /*mainViewModel.sendEvent(
                com.listocalixto.android.rembrandt.navigation.principal.PrincipalUiEvent.NavigateToDisplayImageFragment(
                    args = DisplayArtworkFragmentArgs(
                        hightResolutionImageUrl = viewModel.uiState.value.imageUrl,
                        previousImageMemoryCacheKey = it,
                        altText = viewModel.uiState.value.altText,
                        touchPositionX = touchPositionX,
                        touchPositionY = touchPositionY,
                        zoom = zoom,
                    ),
                    sharedElement = binding?.image ?: return,
                ),
            )*/
        }
    }

    private fun Binding.collectUiStateWithBinding() {
        collectFlowWithLifeCycle(viewModel.uiState) { state ->
            val context = context ?: return@collectFlowWithLifeCycle
            val resources = context.resources ?: return@collectFlowWithLifeCycle
            displayFragmentWHenArtworkIsAlreadyLoaded(state.shouldStartFragmentTransition)
            loadArtworkImage(state.imageUrl, state.imageMemoryCacheKey)
            /*if (!state.shouldShowEnterAnimations) {
                extendedFab?.show()
                chips.forEach { chip ->
                    chip.alpha = 1f
                    chip.y = 0f
                }
            }*/
            if (state.shouldShowEnterAnimations) {
                extendedFab?.show()
                showChipsAnimation(resources)
            } else {
                extendedFab?.show()
                chips.forEach { chip ->
                    chip.alpha = 1f
                    chip.y = 0f
                }
            }
            /*artworkRecommendedAdapter.submitList(state.artworksRecommended)
            extendedFab?.text = if (state.isTranslationDisplayed) {
                resources.getText(Rui.string.frag_artwork_detail_extended_fab_show_original)
            } else {
                resources.getText(Rui.string.frag_artwork_detail_extended_fab_translate)
            }*/
            /*if (state.triggerRefreshAnimation != null) {
                textCategory.fader(viewTrigger = extendedFab)
                textTitle.fader(viewTrigger = extendedFab)
                viewModel.onEvent(RefreshAnimationTriggered)
            }
            val errorMessage = state.errorMessage
            if (errorMessage != null) {
                showSnackbar(root, errorMessage, SHORT) { it.dismiss() }
                viewModel.onEvent(ErrorMessageTriggered)
            }
            if (state.loadingTranslation) {
                linearProgressIndicator?.visible()
            } else {
                linearProgressIndicator?.gone()
            }*/
        }
    }

    private fun Binding.loadArtworkImage(imageUrl: String, imageMemoryCacheKey: String?) {
        image.load(imageUrl) {
            placeholderMemoryCacheKey(imageMemoryCacheKey)
            listener(
                onSuccess = { request, result ->
                    cacheKey = result.memoryCacheKey?.key
                },
            )
        }
    }

    private fun showChipsAnimation(resources: Resources) {
        var startDelay = 0L
        val duration = resources.getInteger(Rui.integer.motion_duration_large).toLong()
        val interpolator = getInterpolator(motionEasingEmphasizedDecelerateInterpolator)
        chips.forEachIndexed { index, chip ->
            startDelay += 150
            val translation = ObjectAnimator.ofFloat(chip, View.TRANSLATION_Y, 0f)
            val alpha = ObjectAnimator.ofFloat(chip, View.ALPHA, 1f)
            AnimatorSet().apply {
                playTogether(translation, alpha)
                this.duration = duration
                this.startDelay = startDelay
                this.interpolator = interpolator
                if (index == chips.lastIndex) {
                    addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            super.onAnimationEnd(animation)
                            viewModel.enterAnimationsShowed()
                            removeAllListeners()
                        }
                    })
                }
                start()
            }
        }
    }

    private fun displayFragmentWHenArtworkIsAlreadyLoaded(shouldStartFragmentTransition: Boolean) {
        if (shouldStartFragmentTransition) startTransition()
    }

    override fun onResume() {
        if (isDarkMode()) {
            val imageAmbientColor = viewModel.uiState.value.imageAmbientColor
            val imageAmbientGradient = viewModel.uiState.value.imageAmbientGradient
            appBar?.setBackgroundColor(imageAmbientColor)
            binding?.gradientView?.background = imageAmbientGradient
        }
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        // viewModel.onEvent(ArtworkDetailUiEvent.SaveCurrentArtworkId)
    }

    companion object {
        // The name for this key needs to be exactly the same as was defined in safe args.
        const val ARTWORK_ID_KEY = "id"
        const val ARTWORK_ID_DEFAULT_VALUE = -1L
        const val MEMORY_CACHE_KEY = "previousImageMemoryKey"
        const val SHOW_ENTER_ANIMATIONS = "showEnterAnimations"
        const val AMBIENT_COLOR_KEY = "previousImageAmbientColor"
    }
}
