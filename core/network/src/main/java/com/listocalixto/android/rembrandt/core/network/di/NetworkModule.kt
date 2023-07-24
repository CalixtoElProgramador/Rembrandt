package com.listocalixto.android.rembrandt.core.network.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(
                Json {
                    encodeDefaults = true
                    ignoreUnknownKeys = true
                    isLenient = true
                    allowSpecialFloatingPointValues = true
                    coerceInputValues = true
                    prettyPrint = true
                },
            )
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.i("ktor_logger", message)
                }
            }
        }
        install(ResponseObserver) {
            onResponse { response ->
                Log.i("http_status", "${response.status.value}")
            }
        }
        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
        /**
         * Apparently Ktor does not send the Authorization Header until the
         * service replies that the user is not authorized. Until then, Ktor
         * thinks of sending the Header. But the problem is that some services
         * don't let you know if you are authorized or not, such is the case
         * with the DeepL API that only returned a 403. So the answer never
         * arrives and Ktor doesn't know how to deserialize something that doesn't
         * exist. Therefore, to avoid problems and confusion, it is very important
         * to let Ktor know to always send the Authorization header.
         * */
        install(Auth) {
            bearer { sendWithoutRequest { true } }
        }
        engine {
            connectTimeout = 10_000
            socketTimeout = 10_000
        }
    }
}
