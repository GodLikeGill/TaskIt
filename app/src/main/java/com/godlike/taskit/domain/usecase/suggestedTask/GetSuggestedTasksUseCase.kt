package com.godlike.taskit.domain.usecase.suggestedTask

import com.godlike.taskit.data.repository.SuggestedTaskRepository
import com.godlike.taskit.domain.model.SuggestedTask
import javax.inject.Inject

class GetSuggestedTasksUseCase @Inject constructor(
    private val repository: SuggestedTaskRepository
){
    suspend operator fun invoke(limit: Int = 10): Result<List<SuggestedTask>> {
        return try {
            Result.success(repository.getSuggestedTasks(limit))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}