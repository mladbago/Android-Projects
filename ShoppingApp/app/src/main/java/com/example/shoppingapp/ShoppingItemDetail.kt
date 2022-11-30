package com.example.shoppingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_shopping_item_detail.*
import kotlinx.android.synthetic.main.list_item.*

class ShoppingItemDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_item_detail)

        val name = intent.getStringExtra("name")
        item_name.text = name

        val price = intent.getStringExtra("price")
        item_price.text = price

        val detail = intent.getStringExtra("details")
        item_details.text = detail
    }
}