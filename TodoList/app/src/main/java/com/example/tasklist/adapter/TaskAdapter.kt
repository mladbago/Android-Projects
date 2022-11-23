package com.example.tasklist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tasklist.R
import com.example.tasklist.TaskFragment
import com.example.tasklist.data.Task
import com.example.tasklist.databinding.ListItemBinding


class TaskAdapter(private val taskList: ArrayList<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){

    class TaskViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.task = task
        }
    }
    private lateinit var binding: ListItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val largeNews = taskList[position]
        holder.bind(largeNews)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

}
//class TaskAdapter(private val taskList : ArrayList<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
//
//    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val tvHeading : TextView = itemView.findViewById(R.id.tvHeading)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,
//        parent, false)
//        return TaskViewHolder(itemView)
//    }
//
//    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
//        val currentItem = taskList[position]
//        holder.tvHeading.text = currentItem.heading
//    }
//
//    override fun getItemCount(): Int {
//        return taskList.size
//    }
//
//}