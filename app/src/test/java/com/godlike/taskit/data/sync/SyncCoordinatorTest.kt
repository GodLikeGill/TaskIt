package com.godlike.taskit.data.sync

import com.godlike.taskit.data.repository.AuthRepositoryImpl
import com.godlike.taskit.data.repository.UserRepositoryImpl
import com.godlike.taskit.data.source.local.entity.TaskEntity
import com.godlike.taskit.domain.model.User
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SyncCoordinatorTest {

    @Test
    fun `pending changes are pushed while a user is logged in`() = runTest {
        val userRepository = mockk<UserRepositoryImpl>()
        val authRepository = mockk<AuthRepositoryImpl>()
        val syncManager = mockk<SyncManager>()
        val task = TaskEntity(id = "1", title = "Milk")

        every { authRepository.observeUser() } returns flowOf(User("u1", "a@b.com"))
        every { syncManager.observePending() } returns flowOf(listOf(task))
        coEvery { userRepository.refreshUser("u1") } just Runs
        coEvery { syncManager.sync("u1") } just Runs
        coEvery { syncManager.pushPending("u1") } just Runs

        SyncCoordinator(authRepository, syncManager, userRepository, backgroundScope).start()
        runCurrent()

        coVerifyOrder {
            userRepository.refreshUser("u1")
            syncManager.sync("u1")
            syncManager.pushPending("u1")
        }

        coVerify(exactly = 1) { userRepository.refreshUser("u1") }
        coVerify(exactly = 1) { syncManager.sync("u1") }
        coVerify(exactly = 1) { syncManager.pushPending("u1") }
    }

    @Test
    fun `guest user triggers no profile refresh, no sync and no push`() = runTest {
        val userRepository = mockk<UserRepositoryImpl>()
        val authRepository = mockk<AuthRepositoryImpl>()
        val syncManager = mockk<SyncManager>()

        every { authRepository.observeUser() } returns flowOf(null)

        SyncCoordinator(authRepository, syncManager, userRepository, backgroundScope).start()
        runCurrent()

        verify(exactly = 1) { authRepository.observeUser() }
        coVerify(exactly = 0) { userRepository.refreshUser(any()) }
        coVerify(exactly = 0) { syncManager.sync(any()) }
        coVerify(exactly = 0) { syncManager.pushPending(any()) }
    }

    @Test
    fun `empty pending list refreshes and syncs once but pushes nothing`() = runTest {
        val userRepository = mockk<UserRepositoryImpl>()
        val authRepository = mockk<AuthRepositoryImpl>()
        val syncManager = mockk<SyncManager>()

        every { authRepository.observeUser() } returns flowOf(User("u1", "a@b.com"))
        every { syncManager.observePending() } returns flowOf(emptyList())
        coEvery { userRepository.refreshUser("u1") } just Runs
        coEvery { syncManager.sync("u1") } just Runs

        SyncCoordinator(authRepository, syncManager, userRepository, backgroundScope).start()
        runCurrent()

        coVerify(exactly = 1) { userRepository.refreshUser("u1") }
        coVerify(exactly = 1) { syncManager.sync("u1") }
        coVerify(exactly = 0) { syncManager.pushPending(any()) }
    }

    @Test
    fun `profile refresh failure does not stop task sync`() = runTest {
        val userRepository = mockk<UserRepositoryImpl>()
        val authRepository = mockk<AuthRepositoryImpl>()
        val syncManager = mockk<SyncManager>()

        every { authRepository.observeUser() } returns flowOf(User("u1", "a@b.com"))
        every { syncManager.observePending() } returns flowOf(emptyList())
        coEvery { userRepository.refreshUser("u1") } throws RuntimeException("Offline")
        coEvery { syncManager.sync("u1") } just Runs

        SyncCoordinator(authRepository, syncManager, userRepository, backgroundScope).start()
        runCurrent()

        coVerify(exactly = 1) { userRepository.refreshUser("u1") }
        coVerify(exactly = 1) { syncManager.sync("u1") }
    }
}