package com.listocalixto.android.rembrandt.di.main

import com.listocalixto.android.rembrandt.data.mapper.local.ArtworkLocalToEntity
import com.listocalixto.android.rembrandt.data.mapper.local.ColorLocalToDomain
import com.listocalixto.android.rembrandt.data.mapper.local.ThumbnailLocalToDomain
import com.listocalixto.android.rembrandt.data.mapper.remote.ArtworkRemoteToEntity
import com.listocalixto.android.rembrandt.data.mapper.remote.ColorRemoteToDomain
import com.listocalixto.android.rembrandt.data.mapper.remote.ThumbnailRemoteToDomain
import com.listocalixto.android.rembrandt.data.repo.ArtworkRepoImpl
import com.listocalixto.android.rembrandt.data.source.local.configuration.ArtworkDao
import com.listocalixto.android.rembrandt.data.source.local.configuration.RembrandtDatabase
import com.listocalixto.android.rembrandt.data.source.local.implementation.LocalArtworkDataSource
import com.listocalixto.android.rembrandt.data.source.local.implementation.LocalArtworkDataSourceImpl
import com.listocalixto.android.rembrandt.data.source.remote.implementation.RemoteArtworkDataSource
import com.listocalixto.android.rembrandt.data.source.remote.implementation.RemoteArtworkDataSourceImpl
import com.listocalixto.android.rembrandt.domain.repo.ArtworkRepo
import com.listocalixto.android.rembrandt.domain.usecase.main.*
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
    abstract fun bindRemoteArtworkDatasource(remoteArtworkDataSourceImpl: RemoteArtworkDataSourceImpl): RemoteArtworkDataSource

    @Binds
    @ViewModelScoped
    abstract fun bindArtworkRepo(artworkRepoImpl: ArtworkRepoImpl): ArtworkRepo

    @Binds
    @ViewModelScoped
    abstract fun bindLocalArtworkDataSource(localArtworkDataSource: LocalArtworkDataSourceImpl): LocalArtworkDataSource

    companion object {

        @Provides
        @ViewModelScoped
        fun provideArtworkDao(database: RembrandtDatabase): ArtworkDao = database.artworkDao()

        @Provides
        @ViewModelScoped
        fun provideGetImageUrlUseCase() = GetImageUrlUseCase()

        @Provides
        @ViewModelScoped
        fun provideColorLocalToDomain() = ColorLocalToDomain()

        @Provides
        @ViewModelScoped
        fun provideThumbnailToDomain() = ThumbnailLocalToDomain()

        @Provides
        @ViewModelScoped
        fun provideArtworkLocalToEntity(
            colorMapper: ColorLocalToDomain,
            thumbnailMapper: ThumbnailLocalToDomain,
        ) = ArtworkLocalToEntity(
            colorMapper = colorMapper,
            thumbnailMapper = thumbnailMapper,
        )

        @Provides
        @ViewModelScoped
        fun provideColorRemoteToDomain() = ColorRemoteToDomain()

        @Provides
        @ViewModelScoped
        fun provideThumbnailRemoteToDomain() = ThumbnailRemoteToDomain()

        @Provides
        @ViewModelScoped
        fun provideArtworkRemoteToEntity(
            colorMapper: ColorRemoteToDomain,
            thumbnailMapper: ThumbnailRemoteToDomain,
        ) = ArtworkRemoteToEntity(
            colorMapper = colorMapper,
            thumbnailMapper = thumbnailMapper,
        )

        @Provides
        @ViewModelScoped
        fun provideHomeUseCases(repo: ArtworkRepo, getImageUrl: GetImageUrlUseCase) = HomeUseCases(
            getArtworksByPage = GetArtworksByPageUseCase(repo, getImageUrl),
            updateArtwork = UpdateArtworkUseCase(repo),
            getArtworkById = GetArtworkByIdUseCase(repo),
        )
    }
}
