package com.godlike.taskit.data.source.network.dto

import com.google.firebase.firestore.PropertyName
import java.util.UUID

data class TaskDto(
    val id: String = UUID.randomUUID().toString(),
    var title: String = "",
    var description: String = "",

    @get:PropertyName("isCompleted")
    @set:PropertyName("isCompleted")
    var isCompleted: Boolean,
)