package com.example.fast_api.di


import com.example.fast_api.data.domain.ToDoListUseCase
import com.example.fast_api.data.domain.ToDoListUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {


    @Provides
    fun provideToDoListUseCase(
        pokemonToDoListUseCase: ToDoListUseCaseImpl
    ): ToDoListUseCase {
        return pokemonToDoListUseCase
    }

}