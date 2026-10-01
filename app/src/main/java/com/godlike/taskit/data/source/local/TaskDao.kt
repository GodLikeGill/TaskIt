package com.godlike.taskit.data.source.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.godlike.taskit.data.source.local.entity.SyncState
import com.godlike.taskit.data.source.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM task WHERE isDeleted = 0")
    fun observeAll(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task WHERE id = :taskId AND isDeleted = 0")
    fun observeById(taskId: String): Flow<TaskEntity?>

    @Query("SELECT * FROM task")
    suspend fun getAll(): List<TaskEntity>

    @Query("SELECT * FROM task WHERE id = :taskId")
    suspend fun getById(taskId: String): TaskEntity?

    @Query("SELECT * FROM task WHERE syncState = :state")
    suspend fun getBySyncState(state: SyncState): List<TaskEntity>

    @Upsert
    suspend fun upsertTask(task: TaskEntity)

    @Query("UPDATE task SET isDeleted = 1, updatedAt = :now, syncState = :state WHERE id = :taskId")
    suspend fun softDelete(taskId: String, now: Long, state: SyncState)

    @Query("UPDATE task SET isCompleted = :isCompleted, updatedAt = :now, syncState = :state WHERE id = :taskId")
    suspend fun updateCompleted(taskId: String, isCompleted: Boolean, now: Long, state: SyncState)

    @Query("DELETE FROM task WHERE id = :taskId")
    suspend fun deleteById(taskId: String)

    @Query("UPDATE task SET syncState = :synced WHERE id = :taskId AND updatedAt = :updatedAt")
    suspend fun markSynced(taskId: String, updatedAt: Long, synced: SyncState = SyncState.SYNCED)
}