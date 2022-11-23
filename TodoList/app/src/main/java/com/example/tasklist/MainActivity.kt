package com.example.tasklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.tasklist.data.TaskViewModel
import com.example.tasklist.database.DatabaseManager

class MainActivity : AppCompatActivity() {
    private val viewModel: TaskViewModel by viewModels()
    private lateinit var databaseTasks: DatabaseManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //swipe works with rotation of the screen
        if (viewModel.getAllTasks().isEmpty() && !viewModel.getFlag()) {
            viewModel.setFlag()
            databaseTasks = DatabaseManager(this)
            viewModel.addFromDatabase(databaseTasks.refactorToList())
        }
        replaceFragment(TaskFragment())
    }

    private fun replaceFragment(taskFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, taskFragment)
        fragmentTransaction.commit()
    }

}