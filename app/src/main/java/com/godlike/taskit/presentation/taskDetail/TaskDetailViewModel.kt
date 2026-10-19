package com.godlike.taskit.presentation.taskDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godlike.taskit.domain.model.Task
import com.godlike.taskit.domain.usecase.task.AddTaskUseCase
import com.godlike.taskit.domain.usecase.task.CompleteTaskUseCase
import com.godlike.taskit.domain.usecase.task.DeleteTaskUseCase
import com.godlike.taskit.domain.usecase.task.GetTaskUseCase
import com.godlike.taskit.nav.TaskItDestinationsArgs.TASK_ID_ARG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
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
    private val addTask: AddTaskUseCase,
    private val deleteTask: DeleteTaskUseCase,
    private val completeTask: CompleteTaskUseCase,
): ViewModel() {
    private val taskId: String = checkNotNull(savedStateHandle[TASK_ID_ARG])

    val uiState: StateFlow<TaskDetailState> = getTask(taskId)
        .map { task -> if (task == null) TaskDetailState.NotFound else TaskDetailState.Loaded(task) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = TaskDetailState.Loading
        )

    fun onSave(task: Task) = viewModelScope.launch { addTask(task) }
    fun onComplete(isCompleted: Boolean) = viewModelScope.launch { completeTask(taskId, isCompleted) }
    fun onDelete() = viewModelScope.launch { deleteTask(taskId) }
}