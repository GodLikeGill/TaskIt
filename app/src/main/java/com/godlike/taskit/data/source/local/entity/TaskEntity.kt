package com.godlike.taskit.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "task")
data class TaskEntity (
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    var title: String = "",
    var description: String = "",
    var isCompleted: Boolean = false,
)