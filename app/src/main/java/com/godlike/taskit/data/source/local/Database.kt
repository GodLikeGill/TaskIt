package com.godlike.taskit.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.godlike.taskit.data.source.local.entity.TaskEntity
import com.godlike.taskit.data.source.local.entity.UserEntity

@Database(
    entities = [TaskEntity::class, UserEntity::class],
    version = 3,
)
abstract class Database : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun userDao(): UserDao
}