package com.example.fast_api.ui.list


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fast_api.data.local.ToDoItemData

@Composable
fun TodoListScreen(
    listViewModel: TodoListViewModel
) {
    when (listViewModel.listUiState) {
        is ListUiState.Loading -> LoadingScreen()
        is ListUiState.Success -> ResultScreen(
            (listViewModel.listUiState as ListUiState.Success).toDoItemData,
        )

        is ListUiState.Error -> ErrorScreen()
    }
}

/**
 * ローディング画面
 */
@Composable
private fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

/**
 * 一覧情報表示画面
 *
 * @param listData
 */
@Composable
private fun ResultScreen(
    listData: List<ToDoItemData>,
) {
    val itemsList by remember { mutableStateOf(listData) }
    LazyColumn {
        items(itemsList) { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.id.toString(),
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = item.noteText,
                    fontSize = 18.sp
                )
            }
        }
    }
}



/**
 * エラー画面
 */
@Composable
private fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "エラー画面")
    }
}

