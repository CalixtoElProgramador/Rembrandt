package com.listocalixto.android.rembrandt.data.mapper.local

import com.listocalixto.android.rembrandt.core.Mapper
import com.listocalixto.android.rembrandt.data.source.local.implementation.model.ColorLocal
import com.listocalixto.android.rembrandt.domain.model.Color

class ColorLocalToDomain : Mapper<ColorLocal, Color> {

    override fun map(value: ColorLocal) = Color(
        h = value.h,
        l = value.l,
        percentage = value.percentage,
        population = value.population,
        s = value.s,
    )

    override fun map(values: List<ColorLocal>): List<Color> {
        return values.map { map(it) }
    }

    override fun reverseMap(value: Color) = ColorLocal(
        h = value.h,
        l = value.l,
        percentage = value.percentage,
        population = value.population,
        s = value.s,
    )

    override fun reverseMap(values: List<Color>): List<ColorLocal> {
        return values.map { reverseMap(it) }
    }
}
