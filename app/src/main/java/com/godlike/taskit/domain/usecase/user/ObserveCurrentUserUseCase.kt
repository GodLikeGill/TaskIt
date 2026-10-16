package com.godlike.taskit.domain.usecase.user

import com.godlike.taskit.data.repository.AuthRepository
import com.godlike.taskit.data.repository.UserRepository
import com.godlike.taskit.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ObserveCurrentUserUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
) {
    operator fun invoke(): Flow<User?> =
        authRepository.observeUser().flatMapLatest { authUser ->
            if (authUser == null) flowOf(null)
            else userRepository.observeUser(authUser.uid).map { profile -> profile ?: authUser }
        }
}