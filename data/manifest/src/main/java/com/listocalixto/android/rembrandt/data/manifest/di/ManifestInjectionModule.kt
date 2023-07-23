package com.listocalixto.android.rembrandt.data.manifest.di

import com.listocalixto.android.rembrandt.core.domain.repository.ManifestRepo
import com.listocalixto.android.rembrandt.data.manifest.ManifestRepoImpl
import com.listocalixto.android.rembrandt.data.manifest.local.LocalManifestDataSource
import com.listocalixto.android.rembrandt.data.manifest.local.LocalManifestDataSourceImpl
import com.listocalixto.android.rembrandt.data.manifest.remote.RemoteManifestDataSource
import com.listocalixto.android.rembrandt.data.manifest.remote.RemoteManifestDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface ManifestInjectionModule {
    @Binds
    fun bindsRemoteManifestDataSource(impl: RemoteManifestDataSourceImpl): RemoteManifestDataSource

    @Binds
    fun bindsLocalManifestDataSource(impl: LocalManifestDataSourceImpl): LocalManifestDataSource

    @Binds
    fun bindsManifestRepo(impl: ManifestRepoImpl): ManifestRepo
}
