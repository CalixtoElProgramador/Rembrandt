package com.listocalixto.android.rembrandt.data.artwork.mapper

import com.listocalixto.android.rembrandt.common.dependencies.Mapper
import com.listocalixto.android.rembrandt.common.entities.model.Color
import com.listocalixto.android.rembrandt.core.local.artwork.dto.LocalColor
import javax.inject.Inject

internal class LocalColorToModel @Inject constructor() : Mapper<LocalColor, Color>() {
    override fun map(input: LocalColor) = Color(
        h = input.h,
        l = input.l,
        percentage = input.percentage,
        population = input.population,
        s = input.s,
    )

    override fun reverseMap(input: Color) = LocalColor(
        h = input.h,
        l = input.l,
        percentage = input.percentage,
        population = input.population,
        s = input.s,
    )
}
