package com.godlike.taskit.data.repository

import com.godlike.taskit.data.source.network.FirebaseAuthDataSource
import com.godlike.taskit.domain.model.User
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authDataSource: FirebaseAuthDataSource
) {

}