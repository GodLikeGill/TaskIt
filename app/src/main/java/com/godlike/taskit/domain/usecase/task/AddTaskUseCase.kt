package com.godlike.taskit.domain.usecase.task

import com.godlike.taskit.data.repository.TaskRepository
import com.godlike.taskit.domain.model.Task
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(private val repository: TaskRepository) {
    suspend operator fun invoke(task: Task) = repository.addTask(task)
}