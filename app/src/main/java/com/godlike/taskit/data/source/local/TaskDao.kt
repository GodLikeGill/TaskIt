package com.godlike.taskit.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.godlike.taskit.data.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun observeAll(): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE id = :taskId")
    fun observeById(taskId: String): Flow<Task>

    @Query("SELECT * FROM task")
    suspend fun getAll(): List<Task>

    @Query("SELECT * FROM task WHERE id = :taskId")
    suspend fun getById(taskId: String): Task?

    @Upsert
    suspend fun upsertTask(task: Task)

    @Query("DELETE FROM task WHERE id = :taskId")
    suspend fun deleteById(taskId: Int)

    @Query("UPDATE task SET isCompleted = :isCompleted WHERE id = :taskId")
    suspend fun updateCompleted(taskId: Int, isCompleted: Boolean)
}