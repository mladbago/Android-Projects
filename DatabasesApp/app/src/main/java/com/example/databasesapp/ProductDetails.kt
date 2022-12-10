package com.example.databasesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databasesapp.adapters.ProductsAdapter
import com.example.databasesapp.models.Product
import kotlinx.android.synthetic.main.activity_product_details.*

class ProductDetails : AppCompatActivity(), OnProductClickListener {

    private var productItems = ArrayList<Product>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        createProductItemsList()

        var productItemAdapter = ProductsAdapter(productItems, this)
        rv_shopping_list.layoutManager = LinearLayoutManager(this)
        rv_shopping_list.adapter = productItemAdapter
        productItemAdapter.notifyDataSetChanged()
    }
    fun createProductItemsList() {

        productItems.add(Product("Banana", "7zl", "Banana is a fruit."))
        productItems.add(Product("Apple", "3zl", "Apple is a fruit."))
        productItems.add(Product("Boots", "200zl", "Timberland Boots"))
        productItems.add(Product("Book", "130zl", "Introduction to Algorithms version 2"))
        productItems.add(Product("Pencil", "3zl", "Schtaedler pencil"))
        productItems.add(Product("Computer", "5000zl", "McBook Pro"))
        productItems.add(Product("Lettuce", "5zl", "Lettuce is a vegetable"))
        productItems.add(Product("Socks", "20zl", "3 pairs of Kappa socks"))
        productItems.add(Product("Contact lenses", "500zl", "Gas-permeable contact lenses"))
        productItems.add(Product("Contact lenses conditioner", "20zl", "Avizor Conditioner"))
        productItems.add(Product("Contact lenses cleaner", "20zl", "Avizor Cleaner Shampoo"))
        productItems.add(Product("Ketchup", "7zl", "Kotlin Ketchup"))
    }

    override fun onProductClicked(position: Int) {
        val text = "Clicked"
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }
}