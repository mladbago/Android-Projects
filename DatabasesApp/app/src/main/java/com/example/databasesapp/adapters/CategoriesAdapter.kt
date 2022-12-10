package com.example.databasesapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.databasesapp.OnCategoryClickListener
import com.example.databasesapp.R
import com.example.databasesapp.models.Category
import com.example.databasesapp.viewholders.CategoryViewHolder
import kotlinx.android.synthetic.main.list_category.view.*

class CategoriesAdapter(private val categoryItems : ArrayList<Category>,
                        private val onCategoryClickListener: OnCategoryClickListener) :
    RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_category, parent,false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categoryItem = categoryItems[position]

        holder.itemView.name_category.text = categoryItem.name
        holder.itemView.tax_category.text = categoryItem.tax

        holder.itemView.setOnClickListener {
            onCategoryClickListener.onCategoryClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return categoryItems.size
    }
}