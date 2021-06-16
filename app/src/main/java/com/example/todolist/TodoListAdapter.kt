package com.example.todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.TodoItemRowBinding

class TodoListAdapter(private val context: Context, private var todoList: ArrayList<String>) :
    RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {


    fun setData(list: List<String>) {
        list
        todoList.clear()
        todoList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListAdapter.ViewHolder {
        val binding: TodoItemRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.todo_item_row,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoListAdapter.ViewHolder, position: Int) {
        holder.bind(todoList[position])
        holder.rowBinding.deleteBtn.setOnClickListener {
            todoList.removeAt(position)
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }


    inner class ViewHolder(var rowBinding: TodoItemRowBinding) :
        RecyclerView.ViewHolder(rowBinding.root) {

        fun bind(s: String) {
            rowBinding.contentMsg.text = s
        }

    }
}