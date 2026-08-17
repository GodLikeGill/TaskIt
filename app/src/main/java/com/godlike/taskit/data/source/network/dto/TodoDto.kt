package com.godlike.taskit.data.source.network.dto

data class TodoDto(
    val id: Int = 0,
    val todo: String = "",
    val completed: Boolean = false,
    val userId: Int = 0,
)