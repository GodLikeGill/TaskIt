package com.godlike.taskit.data.mapper

import com.godlike.taskit.data.source.local.entity.SyncState
import com.godlike.taskit.data.source.local.entity.TaskEntity
import com.godlike.taskit.data.source.network.dto.TaskDto
import com.godlike.taskit.domain.model.Task

/**
 *  DomainTask to TaskEntity
 *  TaskEntity to DomainTask
 *  Mapping
 */

fun TaskEntity.toDomain(): Task {
    return Task(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted,
    )
}

fun Task.toEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted,
    )
}

/**
 *  DomainTask to TaskDto
 *  TaskDto to DomainTask
 *  Mapping
 */

fun TaskDto.toDomain(): Task {
    return Task(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted,
    )
}

fun Task.toDto(): TaskDto {
    return TaskDto(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted,
    )
}

/**
 *  TaskEntity to TaskDto
 *  TaskDto to EntityTask
 *  Mapping
 */

fun TaskEntity.toDto(): TaskDto = TaskDto(
    id = id,
    title = title,
    description = description,
    isCompleted = isCompleted,
    updatedAt = updatedAt,
)

fun TaskDto.toEntity(syncState: SyncState): TaskEntity = TaskEntity(
    id = id,
    title = title,
    description = description,
    isCompleted = isCompleted,
    updatedAt = updatedAt,
    isDeleted = false,
    syncState = syncState,
)