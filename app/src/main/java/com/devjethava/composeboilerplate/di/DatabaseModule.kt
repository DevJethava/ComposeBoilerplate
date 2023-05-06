package com.devjethava.composeboilerplate.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.devjethava.composeboilerplate.database.AppDatabase
import com.devjethava.composeboilerplate.database.repository.user.UserRepository
import com.devjethava.composeboilerplate.database.repository.user.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = AppDatabase.DATABASE_NAME
        ).addMigrations(MIGRATION_1_2).build()

    @Singleton
    @Provides
    fun getUserRepository(
        db: AppDatabase
    ): UserRepository = UserRepositoryImpl(db.userDao)

    private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("DROP TABLE IF EXISTS Location")
        }
    }
}