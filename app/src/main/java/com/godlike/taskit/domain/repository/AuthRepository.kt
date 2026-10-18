package com.godlike.taskit.domain.repository

import com.godlike.taskit.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun observeUser(): Flow<User?>
    suspend fun login(email: String, password: String): User?
    suspend fun register(email: String, password: String): User?
    suspend fun getCurrentUser(): User?
    suspend fun logout()
}