package com.listocalixto.android.rembrandt.core

interface Mapper<T1, T2> {

    fun map(value: T1): T2

    fun reverseMap(value: T2): T1

    fun map(values: List<T1>): List<T2>

    fun reverseMap(values: List<T2>): List<T1>
}
