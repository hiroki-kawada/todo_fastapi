package com.example.fast_api.data.network


import retrofit2.http.GET



interface TodoApiService {

    @GET("tasks")
    suspend fun getTasks(): ResponseData

}

