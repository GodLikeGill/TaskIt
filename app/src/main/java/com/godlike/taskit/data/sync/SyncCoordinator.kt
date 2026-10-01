package com.godlike.taskit.data.sync

import android.util.Log
import com.godlike.taskit.data.repository.AuthRepository
import com.godlike.taskit.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.cancellation.CancellationException

@Singleton
class SyncCoordinator @Inject constructor(
    private val authRepository: AuthRepository,
    private val syncManager: SyncManager,
    @param:ApplicationScope private val scope: CoroutineScope,
) {
    fun start() {
        scope.launch {
            authRepository.observeUser()
                .distinctUntilChanged { old, new -> old?.uid == new?.uid }
                .collectLatest { user ->
                    if (user != null) {
                        try {
                            syncManager.sync(user.uid)
                        } catch (e: CancellationException) {
                            throw e
                        } catch (e: Exception) {
                            Log.w("SyncCoordinator", "Sync failed", e)
                        }
                    }
                }
        }
    }
}