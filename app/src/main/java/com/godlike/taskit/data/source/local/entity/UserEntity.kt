package com.godlike.taskit.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.godlike.taskit.domain.model.Task

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val uid: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatarUrl: String?
)