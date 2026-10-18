package com.godlike.taskit.domain.usecase.user

import com.godlike.taskit.data.repository.AuthRepositoryImpl
import com.godlike.taskit.domain.model.User
import javax.inject.Inject

class CurrentUserUseCase @Inject constructor(private val repository: AuthRepositoryImpl) {
    suspend operator fun invoke(): Result<User?> {
        return try {
            Result.success(repository.getCurrentUser())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}