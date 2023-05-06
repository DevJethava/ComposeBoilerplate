package com.devjethava.composeboilerplate.di

import android.content.Context
import com.devjethava.composeboilerplate.utils.Preference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob())
    }

    @Provides
    @Singleton
    fun providePreference(@ApplicationContext context: Context): Preference {
        return Preference(context)
    }
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope