package com.godlike.taskit.data.source.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.godlike.taskit.data.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE uid = :uid")
    fun observeUser(uid: String): Flow<UserEntity?>
}