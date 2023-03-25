package com.listocalixto.android.rembrandt.domain.model

data class Color(
    val h: Int,
    val l: Int,
    val percentage: Double,
    val population: Int,
    val s: Int,
) {
    companion object {
        val defaultInstance = Color(h = -1, l = -1, percentage = -1.0, population = -1, s = -1)
    }
}
