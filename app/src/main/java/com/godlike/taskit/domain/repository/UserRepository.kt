package com.godlike.taskit.domain.repository

import com.godlike.taskit.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun observeUser(uid: String): Flow<User?>
    suspend fun saveUser(user: User)
    suspend fun refreshUser(uid: String)
}