package dev.luboganev.kspnumberofloadedfilesissue.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.luboganev.kspnumberofloadedfilesissue.remotetime.RemoteTimeApiService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {

    @Singleton
    @Provides
    fun providesRandomJokeService(
        retrofit: Retrofit
    ): RemoteTimeApiService = retrofit.create(RemoteTimeApiService::class.java)
}
