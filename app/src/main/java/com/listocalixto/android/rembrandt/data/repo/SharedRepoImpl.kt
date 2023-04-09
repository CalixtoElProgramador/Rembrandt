package com.listocalixto.android.rembrandt.data.repo

import com.listocalixto.android.rembrandt.data.source.remote.implementation.RemoteSharedDataSource
import com.listocalixto.android.rembrandt.domain.repo.SharedRepo
import javax.inject.Inject

class SharedRepoImpl @Inject constructor(
    private val remoteDataSource: RemoteSharedDataSource
) : SharedRepo {

    override suspend fun translateText(text: String, targetLang: String): String {
        return remoteDataSource.translateText(text, targetLang)
    }
}
