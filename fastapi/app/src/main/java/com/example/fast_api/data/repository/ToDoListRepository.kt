package com.example.fast_api.data.repository

import com.example.fast_api.data.network.RequestData
import com.example.fast_api.data.network.ResponseData
import com.example.fast_api.data.network.RetrofitInstance
import javax.inject.Inject

interface ToDoListRepository {
    suspend fun getToDoList(): ResponseData

    suspend fun addTask(): String

}

class ToDoListRepositoryImpl @Inject constructor() : ToDoListRepository {
    override suspend fun getToDoList(): ResponseData {
        val taskData = RetrofitInstance.retrofitService.getTasks()
        return if (taskData.isSuccessful) {
            taskData.body()!!
        } else {
            ResponseData(listOf())
        }
    }

    override suspend fun addTask(): String {
        val response = RetrofitInstance.retrofitService.addTask(RequestData(id = 111, name = "ka"))

        return if (response.isSuccessful){
            response.message()
        } else{
            "失敗"
        }
    }
}
