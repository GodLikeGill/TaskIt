package com.godlike.taskit.data.source.network.dto

data class UserDto(
    val uid: String = "",
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val avatarUrl: String? = null,
)