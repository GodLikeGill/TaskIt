package com.godlike.taskit.data.repository

import com.godlike.taskit.data.mapper.toDomain
import com.godlike.taskit.data.mapper.toDto
import com.godlike.taskit.data.mapper.toEntity
import com.godlike.taskit.domain.model.Task
import com.godlike.taskit.data.source.local.TaskDao
import com.godlike.taskit.data.source.network.FirebaseDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val local: TaskDao,
    private val remote: FirebaseDataSource,
) {

    fun getTasks(): Flow<List<Task>> {
        return local.observeAll().map { entityList ->
            entityList.map { it.toDomain() }
        }
    }

    suspend fun addTask(task: Task) {
        local.upsertTask(task.toEntity())
        remote.addTask(task.toDto())
    }

    suspend fun deleteTask(taskId: String) {
        local.deleteById(taskId)
        remote.deleteTask(taskId)
    }

    suspend fun completeTask(taskId: String, isCompleted: Boolean) {
        local.updateCompleted(taskId, isCompleted)
        remote.completeTask(taskId, isCompleted)
    }
}