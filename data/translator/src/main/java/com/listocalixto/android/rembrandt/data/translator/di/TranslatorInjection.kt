package com.listocalixto.android.rembrandt.data.translator.di

import com.listocalixto.android.rembrandt.core.domain.repository.TranslatorRepo
import com.listocalixto.android.rembrandt.data.translator.TranslatorRepoImpl
import com.listocalixto.android.rembrandt.data.translator.local.LocalTranslatorDataSource
import com.listocalixto.android.rembrandt.data.translator.local.LocalTranslatorDataSourceImpl
import com.listocalixto.android.rembrandt.data.translator.remote.RemoteTranslatorDataSource
import com.listocalixto.android.rembrandt.data.translator.remote.RemoteTranslatorDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface TranslatorInjection {
    @Binds
    fun bindsRemoteTranslatorDataSource(impl: RemoteTranslatorDataSourceImpl): RemoteTranslatorDataSource

    @Binds
    fun bindsLocalTranslatorDataSource(impl: LocalTranslatorDataSourceImpl): LocalTranslatorDataSource

    @Binds
    fun bindsTranslatorRepo(impl: TranslatorRepoImpl): TranslatorRepo
}
