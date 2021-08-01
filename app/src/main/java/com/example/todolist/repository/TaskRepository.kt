package com.example.todolist.repository

import com.example.todolist.db.TaskDao
import com.example.todolist.db.TaskEntity

class TaskRepository(private val dao: TaskDao) {

    suspend fun addTask(task: TaskEntity){
        dao.addTask(task)
    }

    suspend fun getAllTask():List<TaskEntity>{
        return dao.getAllTask()
    }

    suspend fun deleteTask(task: TaskEntity)=dao.deleteTask(task)
}