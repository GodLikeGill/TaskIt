package com.godlike.taskit.data.source.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.godlike.taskit.data.source.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun observeAll(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task WHERE id = :taskId")
    fun observeById(taskId: String): Flow<TaskEntity>

    @Query("SELECT * FROM task")
    suspend fun getAll(): List<TaskEntity>

    @Query("SELECT * FROM task WHERE id = :taskId")
    suspend fun getById(taskId: String): TaskEntity?

    @Upsert
    suspend fun upsertTask(task: TaskEntity)

    @Query("DELETE FROM task WHERE id = :taskId")
    suspend fun deleteById(taskId: String)

    @Query("UPDATE task SET isCompleted = :isCompleted WHERE id = :taskId")
    suspend fun updateCompleted(taskId: String, isCompleted: Boolean)
}