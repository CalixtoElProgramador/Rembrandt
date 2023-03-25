package com.listocalixto.android.rembrandt.di.main

import com.listocalixto.android.rembrandt.data.mapper.remote.ArtworkRemoteToEntity
import com.listocalixto.android.rembrandt.data.mapper.remote.ColorRemoteToDomain
import com.listocalixto.android.rembrandt.data.mapper.remote.ThumbnailRemoteToDomain
import com.listocalixto.android.rembrandt.data.repo.ArtworkRepoImpl
import com.listocalixto.android.rembrandt.data.source.remote.implementation.RemoteArtworkDataSource
import com.listocalixto.android.rembrandt.data.source.remote.implementation.RemoteArtworkDataSourceImpl
import com.listocalixto.android.rembrandt.domain.repo.ArtworkRepo
import com.listocalixto.android.rembrandt.domain.usecase.main.GetArtworksByPageUseCase
import com.listocalixto.android.rembrandt.domain.usecase.main.GetImageUrlUseCase
import com.listocalixto.android.rembrandt.domain.usecase.main.HomeUseCases
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

    companion object {
        @Provides
        @ViewModelScoped
        fun provideGetImageUrlUseCase() = GetImageUrlUseCase()

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
        )
    }
}
