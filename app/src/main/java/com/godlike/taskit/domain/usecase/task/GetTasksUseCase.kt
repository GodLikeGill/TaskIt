package com.godlike.taskit.domain.usecase.task

import com.godlike.taskit.domain.repository.TaskRepository
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(private val repository: TaskRepository) {
    operator fun invoke() = repository.getTasks()
}