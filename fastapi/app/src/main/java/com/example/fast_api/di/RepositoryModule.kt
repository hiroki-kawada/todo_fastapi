package com.example.fast_api.di

import com.example.fast_api.data.repository.ToDoListRepository
import com.example.fast_api.data.repository.ToDoListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideToDoListRepository(
        toDoListRepository: ToDoListRepositoryImpl
    ): ToDoListRepository {
        return toDoListRepository
    }
}