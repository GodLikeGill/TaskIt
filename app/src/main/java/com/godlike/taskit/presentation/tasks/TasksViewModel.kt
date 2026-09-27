package com.godlike.taskit.presentation.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godlike.taskit.domain.model.SuggestedTask
import com.godlike.taskit.domain.model.Task
import com.godlike.taskit.domain.usecase.suggestedTask.GetSuggestedTasksUseCase
import com.godlike.taskit.domain.usecase.task.AddTaskUseCase
import com.godlike.taskit.domain.usecase.task.CompleteTaskUseCase
import com.godlike.taskit.domain.usecase.task.DeleteTaskUseCase
import com.godlike.taskit.domain.usecase.task.GetTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class SuggestedTasksState {
    object Idle: SuggestedTasksState()
    object Loading: SuggestedTasksState()
    data class Success(val tasks: List<SuggestedTask>): SuggestedTasksState()
    data class Error(val message: String): SuggestedTasksState()
}

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val getTasks: GetTasksUseCase,
    private val addTask: AddTaskUseCase,
    private val deleteTask: DeleteTaskUseCase,
    private val completeTask: CompleteTaskUseCase,
    private val getSuggestedTasks: GetSuggestedTasksUseCase
) : ViewModel() {

    val tasks = getTasks().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    private val _suggestedTasksState = MutableStateFlow<SuggestedTasksState>(SuggestedTasksState.Idle)
    val suggestedTasksState = _suggestedTasksState.asStateFlow()

    fun onAddTask(task: Task) = viewModelScope.launch { addTask(task) }
    fun onDeleteTask(taskId: String) = viewModelScope.launch { deleteTask(taskId) }
    fun onCompleteTask(taskId: String, isCompleted: Boolean) = viewModelScope.launch {
        completeTask(
            taskId,
            isCompleted
        )
    }

    fun loadSuggestedTasks(limit: Int = 10) {
        viewModelScope.launch {
            _suggestedTasksState.value = SuggestedTasksState.Loading
            getSuggestedTasks(limit)
                .onSuccess { _suggestedTasksState.value = SuggestedTasksState.Success(it) }
                .onFailure { _suggestedTasksState.value = SuggestedTasksState.Error(it.localizedMessage ?: "Unknown error!") }
        }
    }
}