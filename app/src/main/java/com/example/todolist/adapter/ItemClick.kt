package com.example.todolist.adapter

import com.example.todolist.db.TaskEntity

interface ItemClick {
    fun onClick(task: TaskEntity)
}