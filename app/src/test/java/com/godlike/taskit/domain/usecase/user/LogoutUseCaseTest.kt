package com.godlike.taskit.domain.usecase.user

import com.godlike.taskit.data.repository.AuthRepository
import com.godlike.taskit.data.source.local.LocalDataWiper
import com.godlike.taskit.data.sync.SyncManager
import com.godlike.taskit.domain.model.User
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class LogoutUseCaseTest {

    private val authRepository = mockk<AuthRepository>()
    private val syncManager = mockk<SyncManager>()
    private val wiper = mockk<LocalDataWiper>()
    private val useCase = LogoutUseCase(authRepository, syncManager, wiper)

    @Test
    fun `logout wipes then signs out when everything is synced`() = runTest {
        coEvery { authRepository.getCurrentUser() } returns User("u1", "a@b.com")
        coEvery { syncManager.pushBeforeLogout("u1") } returns 0
        coEvery { wiper.wipe() } just Runs
        coEvery { authRepository.logout() } just Runs

        val result = useCase()

        assertEquals(LogoutResult.LoggedOut, result)
        coVerifyOrder {
            syncManager.pushBeforeLogout("u1")
            wiper.wipe()
            authRepository.logout()
        }
    }

    @Test
    fun `guest logout does nothing and leaves local data alone`() = runTest {
        coEvery { authRepository.getCurrentUser() } returns null

        val result = useCase()

        assertEquals(LogoutResult.LoggedOut, result)
        coVerify(exactly = 0) { syncManager.pushBeforeLogout(any()) }
        coVerify(exactly = 0) { wiper.wipe() }
        coVerify(exactly = 0) { authRepository.logout() }
    }

    @Test
    fun `logout is blocked and reports the count when tasks are unsynced`() = runTest {
        coEvery { authRepository.getCurrentUser() } returns User("u1", "a@b.com")
        coEvery { syncManager.pushBeforeLogout("u1") } returns 3

        val result = useCase()

        assertEquals(LogoutResult.UnsyncedTasks(3), result)
        coVerify(exactly = 1) { syncManager.pushBeforeLogout("u1") }
        coVerify(exactly = 0) { wiper.wipe() }
        coVerify(exactly = 0) { authRepository.logout() }
    }

    @Test
    fun `forced logout skips the push, then wipes and signs out`() = runTest {
        coEvery { authRepository.getCurrentUser() } returns User("u1", "a@b.com")
        coEvery { wiper.wipe() } just Runs
        coEvery { authRepository.logout() } just Runs

        val result = useCase(force = true)

        assertEquals(LogoutResult.LoggedOut, result)
        coVerify(exactly = 0) { syncManager.pushBeforeLogout(any()) }
        coVerifyOrder {
            wiper.wipe()
            authRepository.logout()
        }
    }
}