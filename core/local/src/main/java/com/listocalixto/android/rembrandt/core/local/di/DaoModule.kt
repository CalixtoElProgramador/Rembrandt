package com.listocalixto.android.rembrandt.core.local.di

import com.listocalixto.android.rembrandt.core.local.RembrandtDatabase
import com.listocalixto.android.rembrandt.core.local.entities.artwork.ArtworkDao
import com.listocalixto.android.rembrandt.core.local.entities.manifest.ManifestDao
import com.listocalixto.android.rembrandt.core.local.entities.translator.TranslatorDao
import com.listocalixto.android.rembrandt.core.local.entities.collection.CollectionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun provideArtworkDao(database: RembrandtDatabase): ArtworkDao = database.artworkDao()

    @Provides
    fun provideManifestDao(database: RembrandtDatabase): ManifestDao = database.manifestDao()

    @Provides
    fun provideTranslatorDao(database: RembrandtDatabase): TranslatorDao = database.translatorDao()

    @Provides
    fun provideCollectionDao(database: RembrandtDatabase): CollectionDao =
        database.collectionDao()
}
