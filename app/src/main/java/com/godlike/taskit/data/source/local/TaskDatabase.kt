package com.godlike.taskit.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.godlike.taskit.data.Task

@Database(
    entities = [Task::class],
    version = 1,
)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}