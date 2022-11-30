package com.example.shoppingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnShoppingItemClickListener {

    var shoppingItems = ArrayList<ShoppingItem>()
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createShoppingItemsList()

        var shoppingItemAdapter = ShoppingItemAdapter(shoppingItems, this)
        rv_shopping_list.layoutManager = LinearLayoutManager(this)
        rv_shopping_list.adapter = shoppingItemAdapter
        shoppingItemAdapter.notifyDataSetChanged()

    }
    fun createShoppingItemsList() {

        shoppingItems.add(ShoppingItem("Banana", "7zl", "Banana is a fruit."))
        shoppingItems.add(ShoppingItem("Apple", "3zl", "Apple is a fruit."))
        shoppingItems.add(ShoppingItem("Boots", "200zl", "Timberland Boots"))
        shoppingItems.add(ShoppingItem("Book", "130zl", "Introduction to Algorithms version 2"))
        shoppingItems.add(ShoppingItem("Pencil", "3zl", "Schtaedler pencil"))
        shoppingItems.add(ShoppingItem("Computer", "5000zl", "McBook Pro"))
        shoppingItems.add(ShoppingItem("Lettuce", "5zl", "Lettuce is a vegetable"))
        shoppingItems.add(ShoppingItem("Socks", "20zl", "3 pairs of Kappa socks"))
        shoppingItems.add(ShoppingItem("Contact lenses", "500zl", "Gas-permeable contact lenses"))
        shoppingItems.add(ShoppingItem("Contact lenses conditioner", "20zl", "Avizor Conditioner"))
        shoppingItems.add(ShoppingItem("Contact lenses cleaner", "20zl", "Avizor Cleaner Shampoo"))
        shoppingItems.add(ShoppingItem("Ketchup", "7zl", "Kotlin Ketchup"))
    }

    override fun onShoppingItemClicked(position: Int) {
        val intent = Intent(this, ShoppingItemDetail::class.java)
        intent.putExtra("name", shoppingItems[position].name)
        intent.putExtra("price", shoppingItems[position].price)
        intent.putExtra("details", shoppingItems[position].details)
        startActivity(intent)
    }
}