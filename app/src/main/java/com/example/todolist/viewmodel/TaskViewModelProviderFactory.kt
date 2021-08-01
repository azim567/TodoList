package com.example.todolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.usecases.TaskUseCase

class TaskViewModelProviderFactory(
        private val taskUseCase: TaskUseCase) :ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TaskViewModel(taskUseCase) as T
    }
}