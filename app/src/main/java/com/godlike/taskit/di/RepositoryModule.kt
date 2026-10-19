package com.godlike.taskit.di

import com.godlike.taskit.data.repository.AuthRepositoryImpl
import com.godlike.taskit.data.repository.TaskRepositoryImpl
import com.godlike.taskit.data.repository.UserRepositoryImpl
import com.godlike.taskit.domain.repository.AuthRepository
import com.godlike.taskit.domain.repository.TaskRepository
import com.godlike.taskit.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTaskRepository(impl: TaskRepositoryImpl): TaskRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
}