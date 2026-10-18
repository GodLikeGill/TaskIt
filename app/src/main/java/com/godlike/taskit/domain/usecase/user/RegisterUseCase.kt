package com.godlike.taskit.domain.usecase.user

import com.godlike.taskit.data.repository.AuthRepositoryImpl
import com.godlike.taskit.data.repository.UserRepositoryImpl
import com.godlike.taskit.domain.model.User
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepositoryImpl, private val userRepository: UserRepositoryImpl
) {
    suspend operator fun invoke(
        email: String, password: String, firstName: String, lastName: String
    ): Result<User?> {
        return try {
            val authUser = authRepository.register(email, password) ?: return Result.success(null)
            val fullUser = authUser.copy(firstName = firstName, lastName = lastName)
            userRepository.saveUser(fullUser)
            Result.success(fullUser)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}