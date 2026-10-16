package com.godlike.taskit.di

import com.godlike.taskit.data.source.network.FirebaseTaskDataSource
import com.godlike.taskit.data.source.network.FirebaseUserDataSource
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseFirestoreModule {

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideTaskRemoteDataSource(firestore: FirebaseFirestore): FirebaseTaskDataSource =
        FirebaseTaskDataSource(firestore)

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(firestore: FirebaseFirestore): FirebaseUserDataSource =
        FirebaseUserDataSource(firestore)
}