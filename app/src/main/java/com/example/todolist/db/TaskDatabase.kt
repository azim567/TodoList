package com.example.todolist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun todoDao(): TaskDao

    companion object {

        private const val DATABASE_NAME = "tasks.db"
        private var dbInstance: TaskDatabase?=null

        fun createDatabase(context: Context): TaskDatabase =
            Room.databaseBuilder(context, TaskDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

        fun getInstance(context: Context): TaskDatabase {

            return dbInstance ?: createDatabase(context).also { dbInstance =it }
        }

    }
}