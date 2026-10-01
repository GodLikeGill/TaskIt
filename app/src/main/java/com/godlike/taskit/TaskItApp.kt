package com.godlike.taskit

import android.app.Application
import com.godlike.taskit.data.sync.SyncCoordinator
import dagger.hilt.android.HiltAndroidApp
import jakarta.inject.Inject

@HiltAndroidApp
class TaskItApp : Application() {

    @Inject
    lateinit var syncCoordinator: SyncCoordinator

    override fun onCreate() {
        super.onCreate()
        syncCoordinator.start()
    }
}