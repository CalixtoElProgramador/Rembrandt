package com.listocalixto.android.rembrandt.common.dependencies

abstract class Mapper<TypeOne, TypeTwo> {
    abstract fun map(input: TypeOne): TypeTwo
    abstract fun reverseMap(input: TypeTwo): TypeOne

    fun map(inputs: List<TypeOne>): List<TypeTwo> {
        return inputs.map { map(it) }
    }

    fun reverseMap(inputs: List<TypeTwo>): List<TypeOne> {
        return inputs.map { reverseMap(it) }
    }
}
