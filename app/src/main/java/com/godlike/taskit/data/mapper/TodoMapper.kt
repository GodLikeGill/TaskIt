package com.godlike.taskit.data.mapper

import com.godlike.taskit.data.source.network.dto.TodoDto
import com.godlike.taskit.domain.model.SuggestedTask

fun TodoDto.toDomain(): SuggestedTask {
    return SuggestedTask(
        id = id,
        description = todo,
        isCompleted = completed,
    )
}