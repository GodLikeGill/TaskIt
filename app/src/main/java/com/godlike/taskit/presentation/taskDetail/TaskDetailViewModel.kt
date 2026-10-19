package com.godlike.taskit.presentation.taskDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godlike.taskit.domain.model.Task
import com.godlike.taskit.domain.usecase.task.GetTaskUseCase
import com.godlike.taskit.nav.TaskItDestinationsArgs.TASK_ID_ARG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

sealed interface TaskDetailState {
    data object Loading : TaskDetailState
    data class Loaded(val task: Task) : TaskDetailState
    data object NotFound : TaskDetailState
}

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTask: GetTaskUseCase,
): ViewModel() {
    private val taskId: String = checkNotNull(savedStateHandle[TASK_ID_ARG])

    val task = getTask(taskId = taskId).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )
}