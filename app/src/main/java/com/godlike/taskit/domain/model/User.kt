package com.godlike.taskit.domain.model

data class User (
    val uid: String,
    val email: String,
    val firstName: String = "",
    val lastName: String = "",
    val avatarUrl: String? = null,
    var taskList: List<Task> = emptyList()
) {
    val fullName: String get() = "$firstName $lastName".trim()
}