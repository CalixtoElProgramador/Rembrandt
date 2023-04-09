package com.listocalixto.android.rembrandt.presentation.ui.main.home

import com.listocalixto.android.rembrandt.domain.entity.Artwork

sealed class HomeItem() {
    data class LargeCarousel(
        val title: String,
        val description: String,
        val items: List<Artwork>
    ) : HomeItem()
}
