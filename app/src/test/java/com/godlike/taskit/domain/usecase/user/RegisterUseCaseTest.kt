package com.godlike.taskit.domain.usecase.user

import com.godlike.taskit.data.repository.AuthRepository
import com.godlike.taskit.data.repository.UserRepository
import com.godlike.taskit.domain.model.User
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class RegisterUseCaseTest {

    private val authRepository = mockk<AuthRepository>()
    private val userRepository = mockk<UserRepository>()
    private lateinit var registerUseCase : RegisterUseCase

    @Before
    fun setup() {
        registerUseCase = RegisterUseCase(authRepository, userRepository)
    }

    @Test
    fun `Register succeeds when auth and saving User succeed`() = runTest {

        //Arrange
        val authUser = User(
            uid = "1",
            email = "john@gmail.com"
        )

        coEvery { authRepository.register("john@gmail.com", "password") } returns authUser
        coEvery { userRepository.saveUser(any()) } just Runs

        val result = registerUseCase(
            "john@gmail.com",
            "password",
            "John",
            "Doe",
        )

        assertTrue(result.isSuccess)
        assertEquals("John Doe", result.getOrNull()?.fullName)
    }

    @Test
    fun `Register returns null when auth register returns null`() = runTest {
        coEvery { authRepository.register("john@gmail.com", "password") } returns null

        val result = registerUseCase(
            "john@gmail.com",
            "password",
            "John",
            "Doe",
        )

        assertTrue(result.isSuccess)
        assertNull(result.getOrNull())
        coVerify(exactly = 0) { userRepository.saveUser(any()) }
    }

    @Test
    fun `Register returns failure when auth registration throws an exception`() = runTest {
        // Arrange
        coEvery { authRepository.register("john@gmail.com", "password") } throws RuntimeException("Network error")

        // Act
        val result = registerUseCase("john@gmail.com", "password", "John", "Doe")

        //Assert
        assertTrue(result.isFailure)

        //Verify
        coVerify(exactly = 0) { userRepository.saveUser(any()) }
    }

    @Test
    fun `Register returns failure when saveUser throws an exception`() = runTest {

        //Arrange
        coEvery { authRepository.register("john@gmail.com", "password") } returns User("1", "john@gmail.com")
        coEvery { userRepository.saveUser(any()) } throws RuntimeException("Network error")

        //Act
        val result = registerUseCase("john@gmail.com", "password", "John", "Doe")

        //Assert
        assertTrue(result.isFailure)

        //Verify
        coVerify(exactly = 1) { authRepository.register(any(),any()) }
        coVerify(exactly = 1) { userRepository.saveUser(any()) }
    }
}