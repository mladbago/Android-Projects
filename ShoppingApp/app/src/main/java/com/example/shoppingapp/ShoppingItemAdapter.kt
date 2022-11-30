package com.example.shoppingapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class ShoppingItemAdapter(private val shoppingItems : ArrayList<ShoppingItem>,
                          private val onShoppingItemClickListener: OnShoppingItemClickListener) :
    RecyclerView.Adapter<ShoppingItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemViewHolder {
        return ShoppingItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent,false))
    }

    override fun onBindViewHolder(holder: ShoppingItemViewHolder, position: Int) {
        val shoppingItem = shoppingItems[position]

        holder.itemView.name_item.text = shoppingItem.name
        holder.itemView.price_item.text = shoppingItem.price

        holder.itemView.setOnClickListener {
            onShoppingItemClickListener.onShoppingItemClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return shoppingItems.size
    }
}