package com.example.databasesapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.databasesapp.OnProductClickListener
import com.example.databasesapp.R
import com.example.databasesapp.models.Product
import com.example.databasesapp.viewholders.ProductViewHolder
import kotlinx.android.synthetic.main.list_product.view.*

class ProductsAdapter(private val productItems : ArrayList<Product>,
                      private val onShoppingItemClickListener: OnProductClickListener) :
    RecyclerView.Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_product, parent,false))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val productItem = productItems[position]

        holder.itemView.name_item.text = productItem.name
        holder.itemView.price_item.text = productItem.price

        holder.itemView.setOnClickListener {
            onShoppingItemClickListener.onProductClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return productItems.size
    }
}