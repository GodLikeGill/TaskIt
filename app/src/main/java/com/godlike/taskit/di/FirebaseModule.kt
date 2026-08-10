package com.godlike.taskit.di

import com.godlike.taskit.data.source.network.FirebaseDataSource
import com.godlike.taskit.data.source.network.FirebaseUserDataSource
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideTaskRemoteDataSource(firestore: FirebaseFirestore): FirebaseDataSource =
        FirebaseDataSource(firestore)

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(firestore: FirebaseFirestore): FirebaseUserDataSource =
        FirebaseUserDataSource(firestore)
}