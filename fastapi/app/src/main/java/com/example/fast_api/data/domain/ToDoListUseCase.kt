package com.example.fast_api.data.domain

import com.example.fast_api.data.local.ToDoItemData
import com.example.fast_api.data.repository.ToDoListRepository
import javax.inject.Inject

interface ToDoListUseCase {
    suspend fun getToDoList(): List<ToDoItemData>
}

class ToDoListUseCaseImpl @Inject constructor(
    private val mToDoListRepository: ToDoListRepository
) : ToDoListUseCase {

    override suspend fun getToDoList(): List<ToDoItemData> {
        val itemList = mutableListOf<ToDoItemData>()
        mToDoListRepository.getToDoList().test.forEach { item ->
            val data = ToDoItemData(item.id, item.name)
            itemList.add(data)
        }
        return itemList
    }

}