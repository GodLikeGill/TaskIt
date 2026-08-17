package com.godlike.taskit.data.source.network.dto

class TodoResponseDto (
    val todos: List<TodoDto> = emptyList(),
    val total: String = "",
    val skip: String = "",
    val limit: String = ""
)
