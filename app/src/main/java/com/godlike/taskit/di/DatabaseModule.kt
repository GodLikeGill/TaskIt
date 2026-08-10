package com.godlike.taskit.di

import android.content.Context
import androidx.room.Room
import com.godlike.taskit.data.source.local.TaskDao
import com.godlike.taskit.data.source.local.Database
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
    fun provideDatabase(@ApplicationContext context: Context): Database =
        Room.databaseBuilder(
                context,
                Database::class.java,
                "taskit.db"
            ).fallbackToDestructiveMigration(dropAllTables = true).build()

    @Provides
    fun provideTaskDao(database: Database): TaskDao = database.taskDao()

    @Provides
    fun provideUserDao(database: Database): UserDao = database.userDao()
}