package com.listocalixto.android.rembrandt.common.dependencies.di

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val dispatcher: RDispatchers)

enum class RDispatchers {
    Default,
    IO,
    Main
}
