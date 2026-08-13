package com.godlike.taskit.data.repository

import com.godlike.taskit.data.mapper.toDomain
import com.godlike.taskit.data.mapper.toDto
import com.godlike.taskit.data.mapper.toEntity
import com.godlike.taskit.data.source.local.UserDao
import com.godlike.taskit.data.source.network.FirebaseUserDataSource
import com.godlike.taskit.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val local: UserDao,
    private val remote: FirebaseUserDataSource
){
    fun observeUser(uid: String): Flow<User?> {
        return local.observeUser(uid = uid).map { entity -> entity?.toDomain() }
    }

    suspend fun saveUser(user: User) {
        local.upsertUser(user.toEntity())
        remote.upsertUser(user.toDto())
    }
}