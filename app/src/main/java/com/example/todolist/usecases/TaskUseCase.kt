package com.example.todolist.usecases

import com.example.todolist.db.TaskEntity
import com.example.todolist.repository.TaskRepository

class TaskUseCase(private val repository: TaskRepository) {
    suspend fun add(task: TaskEntity)=repository.addTask(task)
    suspend fun get()=repository.getAllTask()
    suspend fun delete(task: TaskEntity)=repository.deleteTask(task)
}