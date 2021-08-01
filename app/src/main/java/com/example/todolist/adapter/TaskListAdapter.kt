package com.example.todolist.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.databinding.TaskItemRowBinding
import com.example.todolist.db.TaskEntity
import kotlin.random.Random

class TaskListAdapter(private val context: Context, private var taskList: ArrayList<TaskEntity>, private val itemClick: ItemClick) :
        RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {


    fun setData(list: List<TaskEntity>) {
        taskList.clear()
        taskList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: TaskItemRowBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.task_item_row,
                parent,
                false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(taskList[position])
        holder.rowBinding.deleteBtn.setOnClickListener {
            itemClick.onClick(taskList[position])
        }

    }

    override fun getItemCount(): Int {
        return taskList.size
    }


    inner class ViewHolder(var rowBinding: TaskItemRowBinding) :
            RecyclerView.ViewHolder(rowBinding.root) {

        fun bind(task: TaskEntity) {
            rowBinding.contentMsg.text = task.content
           /* val random= Random.nextInt(0,6)
            when(random){
                0 -> rowBinding.layout.setBackgroundColor(Color.BLUE)
                1 -> rowBinding.layout.setBackgroundColor(Color.MAGENTA)
                2 -> rowBinding.layout.setBackgroundColor(Color.GRAY)
                3 -> rowBinding.layout.setBackgroundColor(Color.YELLOW)
                4 -> rowBinding.layout.setBackgroundColor(Color.CYAN)
                5 -> rowBinding.layout.setBackgroundColor(Color.DKGRAY)
                6 -> rowBinding.layout.setBackgroundColor(Color.LTGRAY)
            }*/
        }

    }
}