package dev.luboganev.kspnumberofloadedfilesissue.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.time.Duration
import javax.inject.Singleton

private val timeout = Duration.ofSeconds(30)

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideJson(): kotlinx.serialization.json.Json = kotlinx.serialization.json.Json {
        ignoreUnknownKeys = true
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(timeout)
        .readTimeout(timeout)
        .build()

    @Singleton
    @Provides
    fun provideRemoteTimeRetrofit(
        okHttpClient: OkHttpClient,
        json: kotlinx.serialization.json.Json,
    ): Retrofit = Retrofit.Builder()
        .baseUrl("http://worldtimeapi.org/api/")
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .client(okHttpClient)
        .build()
}
