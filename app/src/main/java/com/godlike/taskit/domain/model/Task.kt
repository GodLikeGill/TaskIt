package com.godlike.taskit.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.PropertyName
import java.util.UUID

data class Task (
    val id: String = UUID.randomUUID().toString(),
    var title: String = "",
    var description: String = "",
    var isCompleted: Boolean = false,
)