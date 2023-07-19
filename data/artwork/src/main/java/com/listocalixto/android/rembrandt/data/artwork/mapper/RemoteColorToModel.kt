package com.listocalixto.android.rembrandt.data.artwork.mapper

import com.listocalixto.android.rembrandt.common.dependencies.Mapper
import com.listocalixto.android.rembrandt.common.entities.model.Color
import com.listocalixto.android.rembrandt.core.network.artwork.dto.RemoteColor
import javax.inject.Inject

internal class RemoteColorToModel @Inject constructor() : Mapper<RemoteColor, Color>() {

    override fun map(input: RemoteColor) = Color(
        h = input.h,
        l = input.l,
        percentage = input.percentage,
        population = input.population,
        s = input.s,
    )

    override fun reverseMap(input: Color) = RemoteColor(
        h = input.h,
        l = input.l,
        percentage = input.percentage,
        population = input.population,
        s = input.s,
    )
}
