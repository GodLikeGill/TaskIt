package com.godlike.taskit.data.source.network

import com.godlike.taskit.data.source.network.dto.TodoResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TodoApiService {
    @GET("todos")
    suspend fun getTodos(@Query("limit") limit: Int = 10): TodoResponseDto
}