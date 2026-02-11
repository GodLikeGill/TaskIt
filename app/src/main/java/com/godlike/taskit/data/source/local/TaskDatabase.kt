package com.godlike.taskit.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.godlike.taskit.data.source.local.entity.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 2,
)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}