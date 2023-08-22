package com.listocalixto.android.rembrandt.data.collection.di

import com.listocalixto.android.rembrandt.core.domain.repository.CollectionRepo
import com.listocalixto.android.rembrandt.data.collection.DefaultCollectionRepo
import com.listocalixto.android.rembrandt.data.collection.local.LocalCollectionDataSource
import com.listocalixto.android.rembrandt.data.collection.local.LocalCollectionDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface UserCollectionModule {
    @Binds
    fun bindsLocalCollectionDataSource(impl: LocalCollectionDataSourceImpl): LocalCollectionDataSource

    @Binds
    fun bindsCollectionRepo(impl: DefaultCollectionRepo): CollectionRepo
}
