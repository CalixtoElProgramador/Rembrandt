package com.listocalixto.android.rembrandt.core.local.di

import com.listocalixto.android.rembrandt.core.local.RembrandtDatabase
import com.listocalixto.android.rembrandt.core.local.artwork.ArtworkDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun provideArtworkDao(database: RembrandtDatabase): ArtworkDao = database.artworkDao()
}
