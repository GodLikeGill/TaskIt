package com.godlike.taskit.presentation.auth

import app.cash.turbine.test
import com.godlike.taskit.domain.model.User
import com.godlike.taskit.domain.usecase.user.CurrentUserUseCase
import com.godlike.taskit.domain.usecase.user.LoginUseCase
import com.godlike.taskit.domain.usecase.user.LogoutUseCase
import com.godlike.taskit.domain.usecase.user.RegisterUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.*

@OptIn(ExperimentalCoroutinesApi::class)
class AuthViewModelTest {
    private val loginUseCase = mockk<LoginUseCase>()
    private val registerUseCase = mockk<RegisterUseCase>()
    private val currentUserUseCase = mockk<CurrentUserUseCase>()
    private val logoutUseCase = mockk<LogoutUseCase>()
    private lateinit var viewModel: AuthViewModel


    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = AuthViewModel(loginUseCase, registerUseCase, currentUserUseCase, logoutUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `login emits Loading then Success when loginUseCase succeeds`() = runTest {

        //Arrange
        val user = User("1", email = "john@gmail.com")
        coEvery { loginUseCase("john@gmail.com", "password") } returns Result.success(user)

        //Assert
        viewModel.authState.test {
            assertEquals(AuthState.Idle, awaitItem())
            viewModel.login("john@gmail.com", "password")
            assertEquals(AuthState.Loading, awaitItem())
            assertEquals(AuthState.Success(user), awaitItem())
        }
    }

    @Test
    fun `login emits loading then failure when loginUseCase fails`() = runTest {

        //Arrange
        coEvery { loginUseCase("john@gmail.com", "password") } returns Result.failure(
            RuntimeException("Network error"))

        //Assert
        viewModel.authState.test {
            assertEquals(AuthState.Idle, awaitItem())
            viewModel.login("john@gmail.com", "password")
            assertEquals(AuthState.Loading, awaitItem())
            assertEquals(AuthState.Error("Network error"), awaitItem())
        }
    }

    @Test
    fun `register emits Loading then Success when registerUseCase succeeds`() = runTest {

        //Arrange
        val user = User("1", email = "john@gmail.com", "John", "Doe")
        coEvery { registerUseCase("john@gmail.com", "password", "John", "Doe") } returns Result.success(user)

        //Assert
        viewModel.authState.test {
            assertEquals(AuthState.Idle, awaitItem())
            viewModel.register("john@gmail.com", "password", "John", "Doe")
            assertEquals(AuthState.Loading, awaitItem())
            assertEquals(AuthState.Success(user), awaitItem())
        }
    }

    @Test
    fun `register emits loading then failure when registerUseCase fails`() = runTest {

        //Arrange
        coEvery { registerUseCase("john@gmail.com", "password", "John", "Doe") } returns Result.failure(
            RuntimeException("Network error"))

        //Assert
        viewModel.authState.test {
            assertEquals(AuthState.Idle, awaitItem())
            viewModel.register("john@gmail.com", "password", "John", "Doe")
            assertEquals(AuthState.Loading, awaitItem())
            assertEquals(AuthState.Error("Network error"), awaitItem())
        }
    }

    @Test
    fun `register emits loading then failure when user is null`() = runTest {

        //Arrange
        coEvery { registerUseCase("john@gmail.com", "password", "John", "Doe") } returns Result.success(null)

        //Act
        viewModel.authState.test {
            assertEquals(AuthState.Idle, awaitItem())
            viewModel.register("john@gmail.com", "password", "John", "Doe")
            assertEquals(AuthState.Loading, awaitItem())
            assertEquals(AuthState.Error("Unknown error!"), awaitItem())
        }
    }
}