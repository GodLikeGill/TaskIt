package com.godlike.taskit.data.source.network

import com.godlike.taskit.data.source.network.dto.TaskDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.map

class FirebaseDataSource(
    private val firestore: FirebaseFirestore
) {

    private val tasksCollection = firestore.collection("tasks")

    fun getAllTasks() = tasksCollection.snapshots().map { snapshots ->
        snapshots.documents.map { it.toObject(TaskDto::class.java) }
    }

    suspend fun addTask(task: TaskDto) {
        tasksCollection.document(task.id).set(task)
    }

    suspend fun deleteTask(taskId: String) {
        tasksCollection.document(taskId).delete()
    }

    suspend fun completeTask(taskId: String, isCompleted: Boolean) {
        tasksCollection.document(taskId).update("isCompleted", isCompleted)
    }
}