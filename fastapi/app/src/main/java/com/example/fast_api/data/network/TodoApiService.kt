package com.example.fast_api.data.network


import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET



interface TodoApiService {

    @GET("tasks")
    suspend fun getTasks(): Response<ResponseData>

    @GET("add/task")
    suspend fun addTask(@Body body: RequestData): Response<ResponseMessage>
}

