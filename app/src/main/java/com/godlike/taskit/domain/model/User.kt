package com.godlike.taskit.domain.model

data class User (
    val uid: String,
    val email: String,
    val firstName: String = "",
    val lastName: String = "",
    val avatarUrl: String? = null,
) {
    val fullName: String get() = "$firstName $lastName".trim()
}