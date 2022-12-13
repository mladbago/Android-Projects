package com.example.networkapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class ProductAdapter(val context: Context, private val products : ArrayList<ProductItem>) :
    RecyclerView.Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent,false))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]

        holder.itemView.id_item.text = product.id.toString()
        holder.itemView.title_item.text = product.title
        holder.itemView.price_item.text = product.price
        holder.itemView.label_item.text = product.label
    }

    override fun getItemCount(): Int {
        return products.size
    }
}