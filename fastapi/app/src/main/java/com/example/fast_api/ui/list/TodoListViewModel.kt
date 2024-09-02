package com.example.fast_api.ui.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fast_api.data.domain.ToDoListUseCase
import com.example.fast_api.data.local.ToDoItemData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

interface ListUiState {
    data class Success(val toDoItemData: List<ToDoItemData>) : ListUiState
    object Error : ListUiState
    object Loading : ListUiState
}

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val mToDoListUseCase: ToDoListUseCase
) : ViewModel() {
    var listUiState: ListUiState by mutableStateOf(ListUiState.Loading)
        private set

    private fun getTodoList() {
        listUiState = ListUiState.Loading

        viewModelScope.launch {
            listUiState = try {
                val detailResult = mToDoListUseCase.getToDoList()
                ListUiState.Success(detailResult)
            } catch (e: IOException) {
                ListUiState.Error
            }
        }
    }

    init {
        getTodoList()
    }
}