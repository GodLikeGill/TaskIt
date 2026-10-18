package com.godlike.taskit.domain.usecase.user

import com.godlike.taskit.data.repository.AuthRepositoryImpl
import com.godlike.taskit.domain.model.User
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: AuthRepositoryImpl) {
    suspend operator fun invoke(email: String, password: String): Result<User?> {
        return try {
            Result.success(repository.login(email, password))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}