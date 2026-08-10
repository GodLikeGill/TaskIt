package com.godlike.taskit.data.source.local

import androidx.room.Query
import androidx.room.Upsert
import com.godlike.taskit.data.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserDao {

    @Upsert
    suspend fun upsertUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE uid = :uid")
    fun observeUser(uid: String): Flow<UserEntity?>
}