package com.listocalixto.android.rembrandt.data.user.di

import com.listocalixto.android.rembrandt.core.domain.repository.UserRepo
import com.listocalixto.android.rembrandt.data.user.UserRepoImpl
import com.listocalixto.android.rembrandt.data.user.local.UserLocalDataSource
import com.listocalixto.android.rembrandt.data.user.local.UserLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface UserModule {
    @Binds
    fun bindsUserLocalDataSource(impl: UserLocalDataSourceImpl): UserLocalDataSource

    @Binds
    fun bindsUserRepo(impl: UserRepoImpl): UserRepo
}
