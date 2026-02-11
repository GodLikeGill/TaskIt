package com.godlike.taskit.domain.usecase

import com.godlike.taskit.data.repository.TaskRepository
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(private val repository: TaskRepository) {
    operator fun invoke() = repository.getTasks()
}