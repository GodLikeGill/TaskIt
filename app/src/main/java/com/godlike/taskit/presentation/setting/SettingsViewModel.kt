package com.godlike.taskit.presentation.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godlike.taskit.domain.model.User
import com.godlike.taskit.domain.usecase.user.LogoutResult
import com.godlike.taskit.domain.usecase.user.LogoutUseCase
import com.godlike.taskit.domain.usecase.user.ObserveCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    observeCurrentUserUseCase: ObserveCurrentUserUseCase,
    private val logoutUseCase: LogoutUseCase,
): ViewModel() {

    val user: StateFlow<User?> = observeCurrentUserUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    private val _unsyncedCount = MutableStateFlow<Int?>(null)
    val unsyncedCount: StateFlow<Int?> = _unsyncedCount


    fun logout(force: Boolean = false) {
        viewModelScope.launch {
            when (val result = logoutUseCase(force)) {
                LogoutResult.LoggedOut -> _unsyncedCount.value = null
                is LogoutResult.UnsyncedTasks -> _unsyncedCount.value = result.count
            }
        }
    }

    fun dismissLogoutWarning() { _unsyncedCount.value = null }
}
