package com.listocalixto.android.rembrandt.data.artwork.di

import com.listocalixto.android.rembrandt.core.domain.repository.ArtworkRepo
import com.listocalixto.android.rembrandt.data.artwork.ArtworkRepoImpl
import com.listocalixto.android.rembrandt.data.artwork.local.LocalArtworkDataSource
import com.listocalixto.android.rembrandt.data.artwork.local.LocalArtworkDataSourceImpl
import com.listocalixto.android.rembrandt.data.artwork.remote.RemoteArtworkDataSource
import com.listocalixto.android.rembrandt.data.artwork.remote.RemoteArtworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface ArtworkModule {
    @Binds
    fun bindsRemoteArtworkDataSource(impl: RemoteArtworkDataSourceImpl): RemoteArtworkDataSource

    @Binds
    fun bindsLocalArtworkDataSource(impl: LocalArtworkDataSourceImpl): LocalArtworkDataSource

    @Binds
    fun bindsArtworkRepo(impl: ArtworkRepoImpl): ArtworkRepo
}
