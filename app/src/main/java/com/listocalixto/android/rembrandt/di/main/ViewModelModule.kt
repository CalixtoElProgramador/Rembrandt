package com.listocalixto.android.rembrandt.di.main

import com.listocalixto.android.rembrandt.data.repo.ArtworkRepoImpl
import com.listocalixto.android.rembrandt.data.source.local.configuration.ArtworkDao
import com.listocalixto.android.rembrandt.data.source.local.configuration.ManifestDao
import com.listocalixto.android.rembrandt.data.source.local.configuration.RembrandtDatabase
import com.listocalixto.android.rembrandt.data.source.local.implementation.LocalArtworkDataSource
import com.listocalixto.android.rembrandt.data.source.local.implementation.LocalArtworkDataSourceImpl
import com.listocalixto.android.rembrandt.data.source.local.implementation.LocalManifestDataSource
import com.listocalixto.android.rembrandt.data.source.local.implementation.LocalManifestDataSourceImpl
import com.listocalixto.android.rembrandt.data.source.remote.implementation.RemoteArtworkDataSource
import com.listocalixto.android.rembrandt.data.source.remote.implementation.RemoteArtworkDataSourceImpl
import com.listocalixto.android.rembrandt.domain.repo.ArtworkRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    @ViewModelScoped
    abstract fun bindRemoteArtworkDatasource(
        remoteArtworkDataSourceImpl: RemoteArtworkDataSourceImpl
    ): RemoteArtworkDataSource

    @Binds
    @ViewModelScoped
    abstract fun bindArtworkRepo(artworkRepoImpl: ArtworkRepoImpl): ArtworkRepo

    @Binds
    @ViewModelScoped
    abstract fun bindLocalArtworkDataSource(localArtworkDataSource: LocalArtworkDataSourceImpl): LocalArtworkDataSource

    @Binds
    @ViewModelScoped
    abstract fun bindLocalManifestDataSource(localManifestDataSource: LocalManifestDataSourceImpl): LocalManifestDataSource

    companion object {

        @Provides
        @ViewModelScoped
        fun provideArtworkDao(database: RembrandtDatabase): ArtworkDao = database.artworkDao()

        @Provides
        @ViewModelScoped
        fun provideManifestDao(database: RembrandtDatabase): ManifestDao = database.manifestDao()
    }
}
