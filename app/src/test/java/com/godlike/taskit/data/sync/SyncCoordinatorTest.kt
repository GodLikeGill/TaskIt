package com.godlike.taskit.data.sync

import com.godlike.taskit.data.repository.AuthRepository
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
        val authRepository = mockk<AuthRepository>()
        val syncManager = mockk<SyncManager>()
        val task = TaskEntity(id = "1", title = "Milk")

        every { authRepository.observeUser() } returns flowOf(User("u1", "a@b.com"))
        every { syncManager.observePending() } returns flowOf(listOf(task))
        coEvery { syncManager.sync("u1") } just Runs
        coEvery { syncManager.pushPending("u1") } just Runs

        SyncCoordinator(authRepository, syncManager, backgroundScope).start()
        runCurrent()

        coVerifyOrder {
            syncManager.sync("u1")
            syncManager.pushPending("u1")
        }

        coVerify(exactly = 1) { syncManager.sync("u1") }
        coVerify(exactly = 1) { syncManager.pushPending("u1") }
    }

    @Test
    fun `guest user triggers no sync and no push`() = runTest {
        val authRepository = mockk<AuthRepository>()
        val syncManager = mockk<SyncManager>()

        every { authRepository.observeUser() } returns flowOf(null)

        SyncCoordinator(authRepository, syncManager, backgroundScope).start()
        runCurrent()

        verify(exactly = 1) { authRepository.observeUser() }
        coVerify(exactly = 0) { syncManager.sync(any()) }
        coVerify(exactly = 0) { syncManager.pushPending(any()) }
    }

    @Test
    fun `empty pending list syncs once but pushes nothing`() = runTest {
        val authRepository = mockk<AuthRepository>()
        val syncManager = mockk<SyncManager>()

        every { authRepository.observeUser() } returns flowOf(User("u1", "a@b.com"))
        every { syncManager.observePending() } returns flowOf(emptyList())
        coEvery { syncManager.sync("u1") } just Runs

        SyncCoordinator(authRepository, syncManager, backgroundScope).start()
        runCurrent()

        coVerify(exactly = 1) { syncManager.sync("u1") }
        coVerify(exactly = 0) { syncManager.pushPending(any()) }
    }
}