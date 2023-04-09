package com.listocalixto.android.rembrandt.di.shared

import com.listocalixto.android.rembrandt.data.repo.SharedRepoImpl
import com.listocalixto.android.rembrandt.data.source.remote.implementation.RemoteSharedDataSource
import com.listocalixto.android.rembrandt.data.source.remote.implementation.RemoteSharedDataSourceImpl
import com.listocalixto.android.rembrandt.domain.repo.SharedRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    @ViewModelScoped
    abstract fun bindRemoteSharedDataSource(remoteSharedDataSource: RemoteSharedDataSourceImpl): RemoteSharedDataSource

    @Binds
    @ViewModelScoped
    abstract fun bindSharedRepo(sharedRepoImpl: SharedRepoImpl): SharedRepo
}
