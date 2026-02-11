package com.godlike.taskit.presentation.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godlike.taskit.domain.model.Task
import com.godlike.taskit.domain.usecase.AddTaskUseCase
import com.godlike.taskit.domain.usecase.CompleteTaskUseCase
import com.godlike.taskit.domain.usecase.DeleteTaskUseCase
import com.godlike.taskit.domain.usecase.GetTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val getTasks: GetTasksUseCase,
    private val addTask: AddTaskUseCase,
    private val deleteTask: DeleteTaskUseCase,
    private val completeTask: CompleteTaskUseCase
) : ViewModel() {

    val tasks = getTasks().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun onAddTask(task: Task) = viewModelScope.launch { addTask(task) }
    fun onDeleteTask(taskId: String) = viewModelScope.launch { deleteTask(taskId) }
    fun onCompleteTask(taskId: String, isCompleted: Boolean) = viewModelScope.launch {
        completeTask(
            taskId,
            isCompleted
        )
    }
}