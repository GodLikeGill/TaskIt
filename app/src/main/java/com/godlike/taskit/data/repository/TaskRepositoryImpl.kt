package com.godlike.taskit.data.repository

import com.godlike.taskit.data.mapper.toDomain
import com.godlike.taskit.data.mapper.toEntity
import com.godlike.taskit.data.source.local.TaskDao
import com.godlike.taskit.data.source.local.entity.SyncState
import com.godlike.taskit.data.source.network.FirebaseDataSource
import com.godlike.taskit.domain.model.Task
import com.godlike.taskit.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val local: TaskDao,
    private val remote: FirebaseDataSource,
) : TaskRepository {

    override fun getTasks(): Flow<List<Task>> =
        local.observeAll().map { list -> list.map { it.toDomain() } }

    override suspend fun addTask(task: Task) {
        local.upsertTask(task.toEntity().copy(
            updatedAt = System.currentTimeMillis(),
            isDeleted = false,
            syncState = SyncState.PENDING
        ))
    }

    override suspend fun deleteTask(taskId: String) {
        local.softDelete(taskId, System.currentTimeMillis(), SyncState.PENDING)
    }

    override suspend fun completeTask(taskId: String, isCompleted: Boolean) {
        local.updateCompleted(taskId, isCompleted, System.currentTimeMillis(), SyncState.PENDING)
    }
}