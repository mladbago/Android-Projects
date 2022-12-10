package com.example.databasesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databasesapp.adapters.CategoriesAdapter
import com.example.databasesapp.models.Category
import kotlinx.android.synthetic.main.activity_category_details.*


class CategoryDetails : AppCompatActivity(), OnCategoryClickListener {

    private var categoryItems = ArrayList<Category>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_details)

        createCategoryList()

        val categoryItemAdapter = CategoriesAdapter(categoryItems, this)
        rv_category_list.layoutManager = LinearLayoutManager(this)
        rv_category_list.adapter = categoryItemAdapter
        categoryItemAdapter.notifyDataSetChanged()
    }

    fun createCategoryList() {

        categoryItems.add(Category("Fruits", "5000zl"))
        categoryItems.add(Category("Vegetables", "3000zl"))
        categoryItems.add(Category("Technology", "150000zl"))
        categoryItems.add(Category("Basic Medicine", "1200000zl"))
        categoryItems.add(Category("Ophthalmology", "33333333zl"))

    }


    override fun onCategoryClicked(position: Int) {
        val text = "Clicked"
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }
}