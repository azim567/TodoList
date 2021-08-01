package com.example.todolist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.R
import com.example.todolist.adapter.ItemClick
import com.example.todolist.adapter.TaskListAdapter
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.db.TaskDatabase
import com.example.todolist.db.TaskEntity
import com.example.todolist.repository.TaskRepository
import com.example.todolist.usecases.TaskUseCase
import com.example.todolist.viewmodel.TaskViewModel
import com.example.todolist.viewmodel.TaskViewModelProviderFactory

class MainActivity : AppCompatActivity(), ItemClick {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TaskViewModel
    private val taskListAdapter = TaskListAdapter(this, arrayListOf(),this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViewModel()
        viewModel.getAllTask()
        initRecyclerView()
        clickListener()
        observeLiveEvent()

    }

    private fun setupViewModel() {
        val dao = TaskDatabase.createDatabase(applicationContext).todoDao()
        val repository = TaskRepository(dao)
        val taskUseCase = TaskUseCase(repository)
        viewModel = ViewModelProvider(this, TaskViewModelProviderFactory(taskUseCase)).get(TaskViewModel::class.java)
    }

    private fun observeLiveEvent() {

        viewModel.addTaskLiveData.observe(this, Observer {
            if (it) {
                viewModel.getAllTask()
                Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show()
            }

        })

        viewModel.getAllTasksLiveData.observe(this, Observer {
            taskListAdapter.setData(it)
        })

        viewModel.deleteTaskLiveData.observe(this, Observer {
            if (it) {
                viewModel.getAllTask()
                Toast.makeText(this, "Task Deleted Sucessfully", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun initRecyclerView() {
        binding.todoRecyclerView.apply {
            layoutManager= LinearLayoutManager(context)
            adapter = taskListAdapter
        }

    }

    private fun clickListener() {
        binding.addBtn.setOnClickListener {

            if (!binding.inputText.text.isNullOrEmpty()) {
                val content = binding.inputText.text.toString()
                viewModel.insertTask(TaskEntity(0, content))
                binding.inputText.setText("")
                hideKeyboard()
            }
        }
    }

    private fun hideKeyboard() {
        val hide = applicationContext?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        hide.hideSoftInputFromWindow(binding.inputText.windowToken, 0)
    }

    override fun onClick(task: TaskEntity) {
        viewModel.deleteTask(task)
    }
}
