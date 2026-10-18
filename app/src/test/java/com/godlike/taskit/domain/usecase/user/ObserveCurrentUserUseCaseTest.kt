package com.godlike.taskit.domain.usecase.user

import app.cash.turbine.test
import com.godlike.taskit.data.repository.AuthRepositoryImpl
import com.godlike.taskit.data.repository.UserRepositoryImpl
import com.godlike.taskit.domain.model.User
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ObserveCurrentUserUseCaseTest {

    private val authRepository = mockk<AuthRepositoryImpl>()
    private val userRepository = mockk<UserRepositoryImpl>()
    private val useCase = ObserveCurrentUserUseCase(authRepository, userRepository)

    private val authUser = User(uid = "u1", email = "john@gmail.com")
    private val profile = User(uid = "u1", email = "john@gmail.com", firstName = "John", lastName = "Doe")

    @Test
    fun `emits the full profile when Room has one`() = runTest {
        every { authRepository.observeUser() } returns flowOf(authUser)
        every { userRepository.observeUser("u1") } returns flowOf(profile)

        useCase().test {
            assertEquals(profile, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `falls back to the auth user when Room has no profile`() = runTest {
        every { authRepository.observeUser() } returns flowOf(authUser)
        every { userRepository.observeUser("u1") } returns flowOf(null)

        useCase().test {
            assertEquals(authUser, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `emits null for a guest and never reads the profile`() = runTest {
        every { authRepository.observeUser() } returns flowOf(null)

        useCase().test {
            assertNull(awaitItem())
            awaitComplete()
        }
        verify(exactly = 0) { userRepository.observeUser(any()) }
    }

    @Test
    fun `emits again when the profile changes in Room`() = runTest {
        val roomProfile = MutableStateFlow<User?>(null)
        every { authRepository.observeUser() } returns flowOf(authUser)
        every { userRepository.observeUser("u1") } returns roomProfile

        useCase().test {
            assertEquals(authUser, awaitItem())   // nothing in Room yet, so fallback
            roomProfile.value = profile           // profile refresh lands in Room
            assertEquals(profile, awaitItem())    // the UI would update here
            cancelAndIgnoreRemainingEvents()
        }
    }
}