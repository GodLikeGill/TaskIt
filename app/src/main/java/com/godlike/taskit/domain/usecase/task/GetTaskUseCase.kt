package com.godlike.taskit.domain.usecase.task

import com.godlike.taskit.domain.repository.TaskRepository
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(private val repository: TaskRepository) {
    operator fun invoke(taskId: String) = repository.getTask(taskId)
}