package com.godlike.taskit.data.source.network

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class FirebaseAuthDataSource(private val firebaseAuth: FirebaseAuth) {

    suspend fun signInWithEmail(email: String, password: String): FirebaseUser? {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        return result.user
    }

    suspend fun signUpWithEmail(email: String, password: String): FirebaseUser? {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        return result.user
    }

    fun signOut() { firebaseAuth.signOut() }

    fun getCurrentUser(): FirebaseUser? = firebaseAuth.currentUser
}