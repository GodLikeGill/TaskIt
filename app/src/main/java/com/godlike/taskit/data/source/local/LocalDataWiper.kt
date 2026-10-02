package com.godlike.taskit.data.source.local

import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class LocalDataWiper @Inject constructor(
    private val database: AppDatabase,
) {
    suspend fun wipe() = withContext(Dispatchers.IO) {
        database.clearAllTables()
    }
}