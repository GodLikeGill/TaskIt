package com.godlike.taskit.domain.usecase.task

import com.godlike.taskit.data.repository.TaskRepository
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(private val repository: TaskRepository) {
    suspend operator fun invoke(taskId: String) = repository.deleteTask(taskId)
}