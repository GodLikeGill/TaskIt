package com.godlike.taskit.data.sync

import com.godlike.taskit.data.mapper.toDto
import com.godlike.taskit.data.mapper.toEntity
import com.godlike.taskit.data.source.local.TaskDao
import com.godlike.taskit.data.source.local.entity.SyncState
import com.godlike.taskit.data.source.local.entity.TaskEntity
import com.godlike.taskit.data.source.network.FirebaseDataSource
import com.godlike.taskit.data.source.network.dto.TaskDto
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.just
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SyncManagerTest {

    private val local = mockk<TaskDao>()
    private val remote = mockk<FirebaseDataSource>()
    private lateinit var syncManager: SyncManager

    @Before
    fun setup() {
        syncManager = SyncManager(local, remote)
    }

    @Test
    fun `pushPending uploads live task and marks it synced`() = runTest {
        val task = TaskEntity(id = "1", title = "Milk", updatedAt = 100L)
        coEvery { local.getBySyncState(SyncState.PENDING) } returns listOf(task)
        coEvery { remote.addTask("uid", any()) } just Runs
        coEvery { local.markSynced("1", 100L) } just Runs

        syncManager.pushPending("uid")

        coVerify(exactly = 1) { remote.addTask("uid", task.toDto()) }
        coVerify(exactly = 1) { local.markSynced("1", 100L) }
    }

    @Test
    fun `pushPending deletes tombstone remotely then purges it locally`() = runTest {
        val tombstone = TaskEntity(id = "1", title = "Milk", isDeleted = true, updatedAt = 100L)
        coEvery { local.getBySyncState(SyncState.PENDING) } returns listOf(tombstone)
        coEvery { remote.deleteTask("uid", "1") } just Runs
        coEvery { local.deleteById("1") } just Runs

        syncManager.pushPending("uid")

        coVerifyOrder {
            remote.deleteTask("uid", "1")
            local.deleteById("1")
        }
        coVerify(exactly = 0) { remote.addTask(any(), any()) }
        coVerify(exactly = 0) { local.markSynced(any(), any(), any()) }
    }

    @Test
    fun `pushPending propagates upload failure and does not mark task synced`() = runTest {
        val task = TaskEntity(id = "1", title = "Milk", updatedAt = 100L)
        coEvery { local.getBySyncState(SyncState.PENDING) } returns listOf(task)
        coEvery { remote.addTask("uid", any()) } throws RuntimeException("Network error")

        val result = runCatching { syncManager.pushPending("uid") }

        assertTrue(result.isFailure)
        assertEquals("Network error", result.exceptionOrNull()?.message)
        coVerify(exactly = 0) { local.markSynced(any(), any(), any()) }
    }

    @Test
    fun `pullRemote inserts remote task that is missing locally as synced`() = runTest {
        val dto = TaskDto(id = "1", title = "Milk", updatedAt = 100L)
        coEvery { remote.getTasks("uid") } returns listOf(dto)
        coEvery { local.getAll() } returns emptyList()
        coEvery { local.upsertTask(any()) } just Runs

        syncManager.pullRemote("uid")

        coVerify(exactly = 1) { local.upsertTask(dto.toEntity(SyncState.SYNCED)) }
    }

    @Test
    fun `pullRemote overwrites synced local task with remote version`() = runTest {
        val localTask = TaskEntity(
            id = "1", title = "Milk", updatedAt = 50L, syncState = SyncState.SYNCED
        )
        val dto = TaskDto(id = "1", title = "Milk (edited)", updatedAt = 100L)
        coEvery { remote.getTasks("uid") } returns listOf(dto)
        coEvery { local.getAll() } returns listOf(localTask)
        coEvery { local.upsertTask(any()) } just Runs

        syncManager.pullRemote("uid")

        coVerify(exactly = 1) { local.upsertTask(dto.toEntity(SyncState.SYNCED)) }
    }

    @Test
    fun `pullRemote does not touch a task with pending local changes`() = runTest {
        val localTask = TaskEntity(
            id = "1", title = "Milk (my offline edit)", updatedAt = 200L, syncState = SyncState.PENDING
        )
        val dto = TaskDto(id = "1", title = "Milk", updatedAt = 100L)
        coEvery { remote.getTasks("uid") } returns listOf(dto)
        coEvery { local.getAll() } returns listOf(localTask)

        syncManager.pullRemote("uid")

        coVerify(exactly = 0) { local.upsertTask(any()) }
        coVerify(exactly = 0) { local.deleteById(any()) }
    }

    @Test
    fun `pullRemote deletes synced local task that is missing from remote`() = runTest {
        val localTask = TaskEntity(id = "1", title = "Milk", syncState = SyncState.SYNCED)
        coEvery { remote.getTasks("uid") } returns emptyList()
        coEvery { local.getAll() } returns listOf(localTask)
        coEvery { local.deleteById("1") } just Runs

        syncManager.pullRemote("uid")

        coVerify(exactly = 1) { local.deleteById("1") }
    }

    @Test
    fun `pullRemote keeps pending local task that is missing from remote`() = runTest {
        val guestTask = TaskEntity(id = "1", title = "Milk", syncState = SyncState.PENDING)
        coEvery { remote.getTasks("uid") } returns emptyList()
        coEvery { local.getAll() } returns listOf(guestTask)

        syncManager.pullRemote("uid")

        coVerify(exactly = 0) { local.deleteById(any()) }
        coVerify(exactly = 0) { local.upsertTask(any()) }
    }

    @Test
    fun `pushBeforeLogout returns remaining count when upload fails and does not throw`() = runTest {
        val task = TaskEntity(id = "1", title = "Milk", updatedAt = 100L)
        coEvery { local.getBySyncState(SyncState.PENDING) } returns listOf(task)
        coEvery { remote.addTask("uid", any()) } throws RuntimeException("Offline")
        coEvery { local.countLiveBySyncState(SyncState.PENDING) } returns 1

        val remaining = syncManager.pushBeforeLogout("uid")

        assertEquals(1, remaining)
        coVerify(exactly = 0) { local.markSynced(any(), any(), any()) }
    }
}