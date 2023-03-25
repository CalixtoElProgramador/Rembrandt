package com.listocalixto.android.rembrandt.domain.usecase.main

import com.listocalixto.android.rembrandt.domain.entity.Artist
import com.listocalixto.android.rembrandt.domain.entity.Artwork
import com.listocalixto.android.rembrandt.domain.entity.Gallery
import com.listocalixto.android.rembrandt.domain.entity.Museum

data class GetHomeContentResult(
    val artworkOfTheDay: Artwork,
    val artworksRelatedToASearch: List<Artwork>,
    val artworksRecommended: List<Artwork>,
    val artworksByArtist: List<Artwork>,
    val artworksByTechnique: List<Artwork>,
    val artworksByColor: List<Artwork>,
    val artworksByPeriod: List<Artwork>,
    val artworksByArtisticMovement: List<Artwork>,
    val artistsRecommended: List<Artist>,
    val museums: List<Museum>,
    val galleries: List<Gallery>
)
