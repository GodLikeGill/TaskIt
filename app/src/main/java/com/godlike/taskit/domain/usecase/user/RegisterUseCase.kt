package com.godlike.taskit.domain.usecase.user

import com.godlike.taskit.data.repository.AuthRepository
import com.godlike.taskit.data.repository.UserRepository
import com.godlike.taskit.domain.model.User
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ): Result<User?> {
        return try {
            val authUser = authRepository.register(email, password)
                ?: return Result.success(null)
            val fullUser = authUser.copy(firstName = firstName, lastName = lastName)
            userRepository.saveUser(fullUser)
            Result.success(fullUser)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}