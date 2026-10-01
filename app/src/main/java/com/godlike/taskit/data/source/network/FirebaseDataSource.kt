package com.godlike.taskit.data.source.network

import com.godlike.taskit.data.source.network.dto.TaskDto
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class FirebaseDataSource(
    private val firestore: FirebaseFirestore
) {
    // users/{uid}/tasks/{taskId}
    private fun tasksCollection(uid: String): CollectionReference =
        firestore.collection("users").document(uid).collection("tasks")

    suspend fun getTasks(uid: String): List<TaskDto> =
        tasksCollection(uid)
            .get(Source.SERVER)
            .await()
            .documents
            .mapNotNull { it.toObject(TaskDto::class.java) }

    fun getAllTasks(uid: String): Flow<List<TaskDto>> =
        tasksCollection(uid).snapshots().map { snapshot ->
            snapshot.documents.mapNotNull { it.toObject(TaskDto::class.java) }
        }

    suspend fun addTask(uid: String, task: TaskDto) {
        tasksCollection(uid).document(task.id).set(task).await()
    }

    suspend fun deleteTask(uid: String, taskId: String) {
        tasksCollection(uid).document(taskId).delete().await()
    }

    suspend fun completeTask(uid: String, taskId: String, isCompleted: Boolean) {
        tasksCollection(uid).document(taskId).update("isCompleted", isCompleted).await()
    }
}