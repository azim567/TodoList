package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var dataList= arrayListOf<String>()
    private var todoList= arrayListOf<String>()
    private lateinit var todoListAdapter: TodoListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        clickListener()
        initRecyclerView()


    }

    private fun initRecyclerView() {
        todoListAdapter= TodoListAdapter(this,todoList)
        binding.todoRecyclerView.apply {
            hasFixedSize()
            layoutManager= LinearLayoutManager(this@MainActivity)
            adapter=todoListAdapter

        }

    }

    private fun clickListener() {
        binding.addBtn.setOnClickListener{

            if(!binding.inputText.text.isNullOrEmpty()){
                dataList.add(binding.inputText.text.toString())
                binding.inputText.setText("")
                todoListAdapter.setData(dataList)
            }
        }
    }
}
