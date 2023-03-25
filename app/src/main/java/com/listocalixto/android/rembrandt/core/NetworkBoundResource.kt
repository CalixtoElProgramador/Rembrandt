package com.listocalixto.android.rembrandt.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: suspend (ResultType) -> Boolean = { true },
    crossinline onLoading: (data: ResultType?) -> Unit = {}
) = flow {
    // onLoading(null)
    val data = query().first()
    val flow = if (shouldFetch(data)) {
        onLoading(data)
        try {
            val fetchedResult = fetch()
            saveFetchResult(fetchedResult)
            query().map { Result.success(it) }
        } catch (throwable: Throwable) {
            query().map { Result.failure(throwable) }
        }
    } else {
        query().map { Result.success(it) }
    }
    emitAll(flow)
}
