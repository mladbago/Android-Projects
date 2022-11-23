package com.example.tasklist.data

import androidx.lifecycle.ViewModel

data class Task (var name: String,
                 var description: String,
                 var data: String,
                 var status: String)

class  TaskViewModel : ViewModel() {
    private var taskList = ArrayList<Task>()
    private var flag : Boolean = false
//    fun addTask(taskToAdd : Task) {
//        taskList.add(taskToAdd)
//    }
//
//    fun readTask(index : Int) = taskList[index]

    fun getAllTasks() : ArrayList<Task> = taskList

    fun setFlag() {
        flag = true
    }
    fun getFlag() : Boolean{
        return flag
    }
    fun addFromDatabase(addTaskList : ArrayList<Task>) {
        taskList = addTaskList
    }
}
