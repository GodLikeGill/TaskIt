package com.godlike.taskit.domain.usecase.user

import com.godlike.taskit.data.repository.AuthRepository
import com.godlike.taskit.domain.model.User
import javax.inject.Inject

class CurrentUserUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(): Result<User?> {
        return try {
            Result.success(repository.getCurrentUser())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}