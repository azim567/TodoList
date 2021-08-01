package com.example.todolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.db.TaskEntity
import com.example.todolist.usecases.TaskUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(
        private val taskUseCase: TaskUseCase
):ViewModel() {

    private val _addTaskLiveData=MutableLiveData<Boolean>()
    val addTaskLiveData:LiveData<Boolean>
    get() = _addTaskLiveData


    private val _getAllTasksLiveData=MutableLiveData<List<TaskEntity>>()
    val getAllTasksLiveData:LiveData<List<TaskEntity>>
        get() = _getAllTasksLiveData

    private val _deleteTaskLiveData=MutableLiveData<Boolean>()
    val deleteTaskLiveData:LiveData<Boolean>
        get() = _deleteTaskLiveData

    init {
    }

    fun insertTask(task: TaskEntity){
        viewModelScope.launch(Dispatchers.IO){
        taskUseCase.add(task)
            _addTaskLiveData.postValue(true)
        }
    }

    fun getAllTask(){
        viewModelScope.launch (Dispatchers.IO){
            val result=taskUseCase.get()
            _getAllTasksLiveData.postValue(result)
        }
    }

    fun deleteTask(task: TaskEntity){
        viewModelScope.launch (Dispatchers.IO){
             taskUseCase.delete(task)
            _deleteTaskLiveData.postValue(true)
        }
    }
}