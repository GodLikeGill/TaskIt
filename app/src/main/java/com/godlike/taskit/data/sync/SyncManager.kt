package com.godlike.taskit.data.sync

import com.godlike.taskit.data.mapper.toDto
import com.godlike.taskit.data.mapper.toEntity
import com.godlike.taskit.data.source.local.TaskDao
import com.godlike.taskit.data.source.local.entity.SyncState
import com.godlike.taskit.data.source.network.FirebaseDataSource
import javax.inject.Inject

class SyncManager @Inject constructor(
    private val local: TaskDao,
    private val remote: FirebaseDataSource
) {
    suspend fun pushPending(uid: String) {
        local.getBySyncState(SyncState.PENDING).forEach { task ->
            if (task.isDeleted) {
                remote.deleteTask(uid, task.id)
                local.deleteById(task.id)
            } else {
                remote.addTask(uid, task.toDto())
                local.markSynced(task.id, task.updatedAt)
            }
        }
    }

    suspend fun pullRemote(uid: String) {
        val remoteTasks = remote.getTasks(uid)
        val localById = local.getAll().associateBy { it.id }

        remoteTasks.forEach { dto ->
            val existing = localById[dto.id]
            if (existing == null || existing.syncState == SyncState.SYNCED) {
                local.upsertTask(dto.toEntity(SyncState.SYNCED))
            }
        }

        val remoteIds = remoteTasks.map { it.id }.toSet()
        localById.values
            .filter { it.syncState == SyncState.SYNCED && it.id !in remoteIds }
            .forEach { local.deleteById(it.id) }
    }

    suspend fun sync(uid: String) {
        pushPending(uid)
        pullRemote(uid)
    }
}