package com.listocalixto.android.rembrandt.data.mapper.remote

import com.listocalixto.android.rembrandt.core.Mapper
import com.listocalixto.android.rembrandt.data.source.remote.implementation.model.ColorRemote
import com.listocalixto.android.rembrandt.domain.model.Color

class ColorRemoteToDomain : Mapper<ColorRemote, Color> {

    override fun map(value: ColorRemote) = Color(
        h = value.h ?: -1,
        l = value.l ?: -1,
        percentage = value.percentage ?: -1.0,
        population = value.population ?: -1,
        s = value.s ?: -1,
    )

    override fun map(values: List<ColorRemote>): List<Color> {
        return values.map { map(it) }
    }

    override fun reverseMap(value: Color) = ColorRemote(
        h = value.h,
        l = value.l,
        percentage = value.percentage,
        population = value.population,
        s = value.s,
    )

    override fun reverseMap(values: List<Color>): List<ColorRemote> {
        return values.map { reverseMap(it) }
    }
}
