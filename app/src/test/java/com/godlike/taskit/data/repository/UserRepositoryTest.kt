package com.godlike.taskit.data.repository

import com.godlike.taskit.data.mapper.toDomain
import com.godlike.taskit.data.mapper.toEntity
import com.godlike.taskit.data.source.local.UserDao
import com.godlike.taskit.data.source.network.FirebaseUserDataSource
import com.godlike.taskit.data.source.network.dto.UserDto
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class UserRepositoryTest {

    private val local = mockk<UserDao>()
    private val remote = mockk<FirebaseUserDataSource>()
    private lateinit var repository: UserRepository

    @Before
    fun setup() {
        repository = UserRepository(local, remote)
    }

    @Test
    fun `refreshUser saves the remote profile into Room`() = runTest {
        val dto = UserDto(uid = "u1", email = "john@gmail.com", firstName = "John", lastName = "Doe")
        coEvery { remote.getUser("u1") } returns dto
        coEvery { local.upsertUser(any()) } just Runs

        repository.refreshUser("u1")

        coVerify(exactly = 1) { local.upsertUser(dto.toDomain().toEntity()) }
    }

    @Test
    fun `refreshUser leaves Room untouched when no remote profile exists`() = runTest {
        coEvery { remote.getUser("u1") } returns null

        repository.refreshUser("u1")

        coVerify(exactly = 0) { local.upsertUser(any()) }
    }

    @Test
    fun `refreshUser propagates remote failure and does not touch Room`() = runTest {
        coEvery { remote.getUser("u1") } throws RuntimeException("Offline")

        val result = runCatching { repository.refreshUser("u1") }

        assertTrue(result.isFailure)
        assertEquals("Offline", result.exceptionOrNull()?.message)
        coVerify(exactly = 0) { local.upsertUser(any()) }
    }
}