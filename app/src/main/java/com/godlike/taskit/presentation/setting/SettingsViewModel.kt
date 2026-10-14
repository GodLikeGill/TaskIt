package com.godlike.taskit.presentation.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godlike.taskit.domain.model.User
import com.godlike.taskit.domain.usecase.user.CurrentUserUseCase
import com.godlike.taskit.domain.usecase.user.LogoutResult
import com.godlike.taskit.domain.usecase.user.LogoutUseCase
import com.godlike.taskit.presentation.auth.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val currentUserUseCase: CurrentUserUseCase,
    private val logoutUseCase: LogoutUseCase,
): ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    private val _unsyncedCount = MutableStateFlow<Int?>(null)
    val unsyncedCount: StateFlow<Int?> = _unsyncedCount

    private fun executeAuth(block: suspend () -> Result<User?>) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            block().onSuccess { user ->
                _authState.value = if (user != null) {
                    AuthState.Success(user)
                } else {
                    AuthState.Idle
                }
            }.onFailure { error ->
                _authState.value = AuthState.Error(error.localizedMessage ?: "Unknown error!")
            }
        }
    }

    fun currentUser() {
        executeAuth { currentUserUseCase() }
    }

    fun logout(force: Boolean = false) {
        viewModelScope.launch {
            when (val result = logoutUseCase(force)) {
                LogoutResult.LoggedOut -> {
                    _unsyncedCount.value = null
                    _authState.value = AuthState.Idle
                }
                is LogoutResult.UnsyncedTasks -> _unsyncedCount.value = result.count
            }
        }
    }
}
