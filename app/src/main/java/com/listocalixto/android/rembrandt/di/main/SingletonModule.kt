package com.listocalixto.android.rembrandt.di.main

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.listocalixto.android.rembrandt.data.source.local.configuration.RembrandtDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    @Provides
    @Singleton
    fun provideRembrandtDatabase(@ApplicationContext content: Context): RembrandtDatabase {
        return Room.databaseBuilder(
            content,
            RembrandtDatabase::class.java,
            RembrandtDatabase.NAME,
        ).build()
    }

    @Provides
    @Singleton
    fun provideHttpClient() = HttpClient(Android) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
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
        engine {
            connectTimeout = 10_000
            socketTimeout = 10_000
        }
    }
}
