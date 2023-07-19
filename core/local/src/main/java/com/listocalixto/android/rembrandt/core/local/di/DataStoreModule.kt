package com.listocalixto.android.rembrandt.core.local.di

import com.listocalixto.android.rembrandt.core.local.user.UserDataStore
import com.listocalixto.android.rembrandt.core.local.user.UserDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataStoreModule {
    @Binds
    fun bindsUserDataStore(impl: UserDataStoreImpl): UserDataStore
}
