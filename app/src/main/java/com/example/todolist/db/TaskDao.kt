package com.example.todolist.db

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface TaskDao {

    @Insert(onConflict = REPLACE)
    suspend fun addTask(task: TaskEntity)

    @Query("SELECT * FROM task_table")
    suspend fun getAllTask(): List<TaskEntity>

    @Delete()
    suspend fun deleteTask(task: TaskEntity)

}