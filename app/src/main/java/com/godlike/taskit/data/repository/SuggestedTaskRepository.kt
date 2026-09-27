package com.godlike.taskit.data.repository

import com.godlike.taskit.data.mapper.toDomain
import com.godlike.taskit.data.source.network.TodoApiService
import com.godlike.taskit.domain.model.SuggestedTask
import javax.inject.Inject

class SuggestedTaskRepository @Inject constructor(
    private val todoApiService: TodoApiService
) {
    suspend fun getSuggestedTasks(limit: Int = 10): List<SuggestedTask> {
        return todoApiService.getTodos(limit).todos.map { it.toDomain() }
    }
}