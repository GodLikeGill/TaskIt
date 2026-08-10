package com.godlike.taskit.data.source.network

import com.godlike.taskit.data.source.network.dto.UserDto
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseUserDataSource(
    private val firestore: FirebaseFirestore
) {
    private val usersCollection = firestore.collection("users")

    suspend fun getUser(uid: String): UserDto? =
        usersCollection.document(uid).get().await().toObject(UserDto::class.java)

    suspend fun upsertUser(user: UserDto) {
        usersCollection.document(user.uid).set(user)
    }
}