package com.godlike.taskit.data

import com.godlike.taskit.data.source.local.TaskDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {

    val tasks: Flow<List<Task>> = taskDao.observeAll()

    suspend fun addTask(task: Task) {
        taskDao.upsertTask(
            Task(
                title = task.title,
                description = task.description
            )
        )
    }

    suspend fun deleteTask(taskId: Int) {
        taskDao.deleteById(taskId)
    }
}