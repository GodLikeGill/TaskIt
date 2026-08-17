package com.godlike.taskit.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godlike.taskit.domain.model.User
import com.godlike.taskit.domain.usecase.user.CurrentUserUseCase
import com.godlike.taskit.domain.usecase.user.LoginUseCase
import com.godlike.taskit.domain.usecase.user.LogoutUseCase
import com.godlike.taskit.domain.usecase.user.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val user: User) : AuthState()
    data class Error(val message: String) : AuthState()
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val currentUserUseCase: CurrentUserUseCase,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    private fun executeAuth(block: suspend () -> Result<User?>) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            block().onSuccess { user ->
                    _authState.value = if (user != null) {
                        AuthState.Success(user)
                    } else {
                        AuthState.Error("Unknown error!")
                    }
                }.onFailure { error ->
                    _authState.value = AuthState.Error(error.localizedMessage ?: "Unknown error!")
                }
        }
    }

    fun login(email: String, password: String) {
        executeAuth { loginUseCase(email, password) }
    }

    fun register(email: String, password: String, firstName: String, lastName: String) {
        executeAuth {
            registerUseCase(email, password, firstName, lastName)
        }
    }

    fun currentUser() {
        executeAuth { currentUserUseCase() }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
            _authState.value = AuthState.Idle
        }
    }
}