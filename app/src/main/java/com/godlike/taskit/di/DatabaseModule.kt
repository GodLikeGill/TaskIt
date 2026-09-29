package com.godlike.taskit.di

import android.content.Context
import androidx.room.Room
import com.godlike.taskit.data.source.local.TaskDao
import com.godlike.taskit.data.source.local.AppDatabase
import com.godlike.taskit.data.source.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "taskit.db"
            ).fallbackToDestructiveMigration(dropAllTables = true).build()

    @Provides
    fun provideTaskDao(appDatabase: AppDatabase): TaskDao = appDatabase.taskDao()

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao()
}