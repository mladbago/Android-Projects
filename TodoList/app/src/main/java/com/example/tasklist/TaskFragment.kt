package com.example.tasklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasklist.adapter.TaskAdapter
import com.example.tasklist.data.Task
import com.example.tasklist.data.TaskViewModel
import com.example.tasklist.database.DatabaseManager
import com.example.tasklist.databinding.FragmentTaskBinding

class TaskFragment() : Fragment() {


    private lateinit var adapter: TaskAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var taskArrayList: ArrayList<Task>

    private lateinit var binding: FragmentTaskBinding
    private val viewModel: TaskViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        binding = FragmentTaskBinding.inflate(inflater)
//        return binding.root
        return inflater.inflate(R.layout.fragment_task, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val jobs = ArrayList<Task>()
//        jobs.add(Task("Name: Chores", "Description: Wash the dishes", "Data: 55 Dishes", "Status: Done"))
//        jobs.add(Task("Name: School", "Description: Homework", "Data: Android", "Status: Do in 5 minutes"))
//        jobs.add(Task("Name: School", "Description: Homework", "Data: C#", "Status: Do in 5 minutes"))
//        jobs.add(Task("Name: School", "Description: Homework", "Data: Java", "Status: Do on Friday"))
//        jobs.add(Task("Name: School", "Description: Homework", "Data: Java", "Status: Do on Friday"))
//        jobs.add(Task("Name: School", "Description: Homework", "Data: Java", "Status: Do on Friday"))
//        jobs.add(Task("Name: School", "Description: Homework", "Data: Java", "Status: Do on Friday"))
//
//        viewModel.addTask(jobs[0])
//        viewModel.addTask(jobs[1])
//        viewModel.addTask(jobs[2])

//        initialize()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = layoutManager
//        recyclerView.setHasFixedSize(true)
        adapter = TaskAdapter(viewModel.getAllTasks())
        recyclerView.adapter = adapter
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                return makeMovementFlags(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT)
            }
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                viewModel.getAllTasks().removeAt(viewHolder.adapterPosition)
                recyclerView.adapter?.notifyItemRemoved(viewHolder.adapterPosition)
            }
        }).attachToRecyclerView(recyclerView)

    }
}