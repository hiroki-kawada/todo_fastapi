package com.example.fast_api.data.repository

import com.example.fast_api.data.network.ResponseData
import com.example.fast_api.data.network.RetrofitInstance
import javax.inject.Inject

interface ToDoListRepository {
    suspend fun getToDoList(): ResponseData

}

class ToDoListRepositoryImpl @Inject constructor() : ToDoListRepository {
    override suspend fun getToDoList(): ResponseData {
        val taskData = RetrofitInstance.retrofitService.getTasks()
        return taskData
    }
}
