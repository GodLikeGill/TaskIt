package com.godlike.taskit.data.repository

import com.godlike.taskit.data.source.network.FirebaseAuthDataSource
import com.godlike.taskit.domain.model.User
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authDataSource: FirebaseAuthDataSource
) {
    suspend fun login(email: String, password: String): User? {
        return authDataSource.signInWithEmail(email, password)?.let { firebaseUser ->
            User(uid = firebaseUser.uid, email = firebaseUser.email.orEmpty())
        }
    }

    suspend fun register(email: String, password: String): User? {
        return authDataSource.signUpWithEmail(email, password)?.let { firebaseUser ->
            User(uid = firebaseUser.uid, email = firebaseUser.email.orEmpty())
        }
    }

    suspend fun logout() {
        authDataSource.signOut()
    }

    fun getCurrentUser(): User? {
        return authDataSource.getCurrentUser()?.let { firebaseUser ->
            User(uid = firebaseUser.uid, email = firebaseUser.email.orEmpty())
        }
    }
}