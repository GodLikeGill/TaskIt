package com.godlike.taskit.domain.model

data class SuggestedTask(
    val id: Int,
    val description: String,
    val isCompleted: Boolean,
)