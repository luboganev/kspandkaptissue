package dev.luboganev.kspnumberofloadedfilesissue.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.time.Clock

@Module
@InstallIn(SingletonComponent::class)
class LocalTimeModule {

    @Provides
    fun provideSystemDefaultZoneClock(): Clock = Clock.systemDefaultZone()
}
