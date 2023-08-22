package com.listocalixto.android.rembrandt.feature.artworkdetail

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.PointF
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.res.ResourcesCompat.getDrawable
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import coil.load
import com.google.android.material.R.attr.motionEasingEmphasizedDecelerateInterpolator
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.listocalixto.android.rembrandt.core.ui.R.drawable.ic_favorite
import com.listocalixto.android.rembrandt.core.ui.R.drawable.ic_favorite_outlined
import com.listocalixto.android.rembrandt.core.ui.R.drawable.ic_translate
import com.listocalixto.android.rembrandt.core.ui.R.string.copied_to_the_clipboard
import com.listocalixto.android.rembrandt.core.ui.R.string.save
import com.listocalixto.android.rembrandt.core.ui.R.string.saved
import com.listocalixto.android.rembrandt.core.ui.adapter.RecommendedArtworkAdapter
import com.listocalixto.android.rembrandt.core.ui.extensions.EmphasisType
import com.listocalixto.android.rembrandt.core.ui.extensions.adjustSizeAccordingScroll
import com.listocalixto.android.rembrandt.core.ui.extensions.applyContainerTransformEnterTransition
import com.listocalixto.android.rembrandt.core.ui.extensions.applyFadeThroughEnterTransition
import com.listocalixto.android.rembrandt.core.ui.extensions.applyFadeThroughExitTransition
import com.listocalixto.android.rembrandt.core.ui.extensions.clipboardImage
import com.listocalixto.android.rembrandt.core.ui.extensions.collectFlowWithLifeCycle
import com.listocalixto.android.rembrandt.core.ui.extensions.emphasizes
import com.listocalixto.android.rembrandt.core.ui.extensions.fader
import com.listocalixto.android.rembrandt.core.ui.extensions.getInterpolator
import com.listocalixto.android.rembrandt.core.ui.extensions.isDarkMode
import com.listocalixto.android.rembrandt.core.ui.extensions.showSnackbar
import com.listocalixto.android.rembrandt.core.ui.extensions.startFragmentTransition
import com.listocalixto.android.rembrandt.core.ui.navigation.PrincipalFragment
import com.listocalixto.android.rembrandt.core.ui.utility.ColorContainerType
import com.listocalixto.android.rembrandt.core.ui.utility.SnackbarDuration
import com.listocalixto.android.rembrandt.core.ui.utility.UiText
import com.ortiz.touchview.OnTouchCoordinatesListener
import dagger.hilt.android.AndroidEntryPoint
import com.listocalixto.android.rembrandt.common.designsystem.R as RDS
import com.listocalixto.android.rembrandt.core.ui.R as Rui
import com.listocalixto.android.rembrandt.feature.artworkdetail.databinding.FragmentArtworkDetailBinding as Binding

@AndroidEntryPoint
class ArtworkDetailFragment : Fragment(R.layout.fragment_artwork_detail) {

    private val viewModel: ArtworkDetailViewModel by viewModels()
    private val recommendedArtworkAdapter = RecommendedArtworkAdapter(::navigateToSelf)
    private val chips = mutableListOf<Chip>()
    private val principalFragment: PrincipalFragment?
        get() = ((parentFragment as? NavHostFragment)?.parentFragment as? PrincipalFragment)

    private var binding: Binding? = null
    private var extendedFab: ExtendedFloatingActionButton? = null
    private var smallFab: FloatingActionButton? = null
    private var linearProgressIndicator: LinearProgressIndicator? = null
    private var appBar: AppBarLayout? = null
    private var containerFABs: CoordinatorLayout? = null
    private var cacheKey: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val colorSurface = com.google.android.material.R.attr.colorSurface
        applyFadeThroughEnterTransition()
        principalFragment?.let {
            applyContainerTransformEnterTransition(
                drawingViewIdRes = it.navHostFragmentIdRes,
                colorContainerType = ColorContainerType.AllContainerColors(colorSurface),
            )
        }
    }

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
                chipCopy,
                chipDownloadImage,
                chipAddCollection,
                chipShare,
            ),
        )
        chips.toList()
    }

    private fun Binding.setupInternalViews() {
        lifecycleOwner = this@ArtworkDetailFragment.viewLifecycleOwner
        artworkDetailViewModel = viewModel
        listRecommended.adapter = recommendedArtworkAdapter
        artworkDetailFragment = this@ArtworkDetailFragment
        setupArtworkImageClick()
        gradientView.isVisible = isDarkMode()
    }

    fun onChipCopy(image: ImageView) {
        image.clipboardImage()
        showSnackbar(containerFABs, UiText.StringResource(copied_to_the_clipboard))
    }

    fun onSaveToCollection() {
        val artworkId = viewModel.uiState.value.artworkId
        principalFragment?.showSaveToCollectionBottomSheet(artworkId)
    }

    private fun Binding.setupArtworkImageClick() {
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
    }

    private fun initExternalViews() {
        principalFragment?.let {
            extendedFab = activity?.findViewById(it.extendedFabIdRes)
            setupSmallFab(it)
            linearProgressIndicator = activity?.findViewById(it.linearProgressIdRes)
            appBar = activity?.findViewById(it.appBarIdRes)
            containerFABs = activity?.findViewById(it.containerFABs)
        }
    }

    private fun setupSmallFab(it: PrincipalFragment) {
        smallFab = if (viewModel.shouldShowTranslatorButton()) {
            activity?.findViewById(it.smallFabIdRes)
        } else {
            null
        }
        smallFab?.apply {
            setImageDrawable(getDrawable(resources, ic_translate, activity?.theme))
            setOnClickListener { viewModel.onTranslateClick() }
        }
    }

    private fun Binding.setupExtendedFab() {
        extendedFab?.adjustSizeAccordingScroll(scrollContainer)
        extendedFab?.setOnClickListener { viewModel.toggleFavorite() }
    }

    private fun navigateToSelf(artworkId: Long, memoryCacheKey: String?, gradientColor: Int) {
        applyFadeThroughExitTransition()
        principalFragment?.navigateToArtworkDetail(
            artworkId = artworkId,
            imageMemoryCacheKey = memoryCacheKey,
            shouldShowEnterAnimations = false,
            imageAmbientColor = gradientColor,
            comesFrom = viewModel.uiState.value.comesFrom,
        )
    }

    fun navigateToDisplayArtwork(
        touchPositionX: Float,
        touchPositionY: Float,
        zoom: Float,
    ) {
        cacheKey?.let {
            val image = binding?.image ?: return
            val transitionName = getString(Rui.string.display_artwork_transition_name)
            val extras = FragmentNavigatorExtras(image to transitionName)
            principalFragment?.navigateToDisplayImage(
                imageUrl = viewModel.uiState.value.imageUrl,
                alternativeText = viewModel.uiState.value.alternativeText,
                previousImageMemoryCacheKey = it,
                touchPositionX = touchPositionX,
                touchPositionY = touchPositionY,
                zoom = zoom,
                extras = extras,
            )
        }
    }

    private fun Binding.collectUiStateWithBinding() {
        collectFlowWithLifeCycle(viewModel.uiState) { state ->
            loadArtworkImage(state.imageUrl, state.imageMemoryCacheKey)
            shouldShowEnterAnimations(state.triggerEnterAnimations, state.wereEnterAnimationsShown)
            recommendedArtworkAdapter.submitList(state.recommendedArtworks)
            setExtendedFabViewAccordingTranslationState(state.shouldShowOriginalLanguage)
            setSmallFabIconAccordingState(state.isFavorite)
            coordinateAnimationsInTranslations(
                state.isLoadingTranslation,
                state.triggerTranslationAnimation,
            )
            if (state.errorMessage != null) {
                showSnackbar(
                    root,
                    state.errorMessage,
                    duration = SnackbarDuration.LONG,
                    isAnError = true,
                )
            }
            displayFragmentWHenArtworkIsAlreadyLoaded(state.shouldStartFragmentTransition)
        }
    }

    private fun setSmallFabIconAccordingState(isFavorite: Boolean) {
        @StringRes
        val fabText = if (isFavorite) saved else save

        @DrawableRes
        val drawable = if (isFavorite) ic_favorite else ic_favorite_outlined
        extendedFab?.apply {
            icon = getDrawable(resources, drawable, activity?.theme)
            text = resources.getText(fabText)
        }
    }

    private fun Binding.coordinateAnimationsInTranslations(
        loadingTranslation: Boolean,
        triggerTranslationAnimation: Unit?,
    ) {
        linearProgressIndicator?.isVisible = loadingTranslation
        if (loadingTranslation) {
            textCategory.emphasizes(EmphasisType.Disable)
            textTitle.emphasizes(EmphasisType.Disable)
            textNameArtist.emphasizes(EmphasisType.Disable)
        }

        if (triggerTranslationAnimation != null) {
            textCategory.fader(emphasisType = EmphasisType.Medium)
            textTitle.fader(emphasisType = EmphasisType.High)
            textNameArtist.fader(emphasisType = EmphasisType.Medium)
        }
    }

    private fun setExtendedFabViewAccordingTranslationState(shouldShowOriginalLanguage: Boolean) {
    }

    private fun shouldShowEnterAnimations(
        triggerEnterAnimations: Unit?,
        wereEnterAnimationsShown: Boolean,
    ) {
        extendedFab?.show()
        smallFab?.show()
        if (triggerEnterAnimations != null) {
            extendedFab?.extend()
            showChipsAnimation()
        }
        if (wereEnterAnimationsShown) {
            chips.forEach { chip ->
                chip.alpha = 1f
                chip.y = 0f
            }
        }
    }

    private fun Binding.loadArtworkImage(imageUrl: String, imageMemoryCacheKey: String?) {
        image.load(imageUrl) {
            placeholderMemoryCacheKey(imageMemoryCacheKey)
            listener(
                onSuccess = { _, result ->
                    cacheKey = result.memoryCacheKey?.key
                },
            )
        }
    }

    private fun showChipsAnimation() {
        val duration = resources.getInteger(RDS.integer.motion_duration_large).toLong()
        var startDelay = resources.getInteger(RDS.integer.motion_duration_medium).toLong()
        val interpolator = getInterpolator(motionEasingEmphasizedDecelerateInterpolator)
        chips.forEachIndexed { index, chip ->
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
                            viewModel.enterAnimationsTriggered()
                            removeAllListeners()
                        }
                    })
                }
                start()
            }
            startDelay += (resources.getInteger(RDS.integer.motion_duration_medium).toLong()) / 2
        }
    }

    private fun displayFragmentWHenArtworkIsAlreadyLoaded(shouldStartFragmentTransition: Boolean) {
        if (shouldStartFragmentTransition) startFragmentTransition()
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

    companion object {
        // The name for this key needs to be exactly the same as was defined in safe args.
        const val ARTWORK_ID_KEY = "id"
        const val MEMORY_CACHE_KEY = "previousImageMemoryKey"
        const val SHOW_ENTER_ANIMATIONS = "showEnterAnimations"
        const val AMBIENT_COLOR_KEY = "previousImageAmbientColor"
        const val COMES_FROM_KEY = "comesFrom"
    }
}
