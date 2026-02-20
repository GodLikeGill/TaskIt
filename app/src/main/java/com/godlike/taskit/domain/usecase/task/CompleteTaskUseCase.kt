package com.godlike.taskit.domain.usecase.task

import com.godlike.taskit.data.repository.TaskRepository
import javax.inject.Inject

class CompleteTaskUseCase @Inject constructor(private val repository: TaskRepository) {
    suspend operator fun invoke(taskId: String, isCompleted: Boolean) =
        repository.completeTask(taskId, isCompleted)
}