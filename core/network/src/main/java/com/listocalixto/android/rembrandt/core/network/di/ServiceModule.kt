package com.listocalixto.android.rembrandt.core.network.di

import com.listocalixto.android.rembrandt.core.network.artwork.ArtworkService
import com.listocalixto.android.rembrandt.core.network.artwork.ArtworkServiceImpl
import com.listocalixto.android.rembrandt.core.network.manifest.ManifestService
import com.listocalixto.android.rembrandt.core.network.manifest.ManifestServiceImpl
import com.listocalixto.android.rembrandt.core.network.translator.TranslatorService
import com.listocalixto.android.rembrandt.core.network.translator.TranslatorServiceImpl
import com.listocalixto.android.rembrandt.core.network.user.UserService
import com.listocalixto.android.rembrandt.core.network.user.UserServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface ServiceModule {

    @Binds
    fun bindsArtworkService(impl: ArtworkServiceImpl): ArtworkService

    @Binds
    fun bindsManifestService(impl: ManifestServiceImpl): ManifestService

    @Binds
    fun bindsTranslatorService(impl: TranslatorServiceImpl): TranslatorService

    @Binds
    fun bindsUserService(impl: UserServiceImpl): UserService
}
