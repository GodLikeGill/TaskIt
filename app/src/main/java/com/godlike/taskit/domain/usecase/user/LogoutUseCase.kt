package com.godlike.taskit.domain.usecase.user

import com.godlike.taskit.data.repository.AuthRepository
import com.godlike.taskit.data.source.local.LocalDataWiper
import com.godlike.taskit.data.sync.SyncManager
import javax.inject.Inject

sealed interface LogoutResult {
    data object LoggedOut : LogoutResult
    data class UnsyncedTasks(val count: Int) : LogoutResult
}

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val syncManager: SyncManager,
    private val localDataWiper: LocalDataWiper,
) {
    suspend operator fun invoke(force: Boolean = false): LogoutResult {
        val user = authRepository.getCurrentUser() ?: return LogoutResult.LoggedOut

        if (!force) {
            val unsynced = syncManager.pushBeforeLogout(user.uid)
            if (unsynced > 0) return LogoutResult.UnsyncedTasks(unsynced)
        }

        localDataWiper.wipe()
        authRepository.logout()
        return LogoutResult.LoggedOut
    }
}