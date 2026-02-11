package com.godlike.taskit.domain.usecase

import com.godlike.taskit.domain.model.Task
import com.godlike.taskit.data.repository.TaskRepository
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(private val repository: TaskRepository) {
    suspend operator fun invoke(task: Task) = repository.addTask(task)
}