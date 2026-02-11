package com.godlike.taskit.domain.usecase

import com.godlike.taskit.data.repository.TaskRepository
import javax.inject.Inject

class CompleteTaskUseCase @Inject constructor(private val repository: TaskRepository) {
    suspend operator fun invoke(taskId: String, isCompleted: Boolean) =
        repository.completeTask(taskId, isCompleted)
}