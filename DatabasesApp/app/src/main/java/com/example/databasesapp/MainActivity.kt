package com.example.databasesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val but = findViewById<Button>(R.id.ProductButton)
        but.setOnClickListener {
            val intent = Intent(this, ProductDetails::class.java)
            startActivity(intent)
        }

        val butCategory = findViewById<Button>(R.id.CategoryButton)
        butCategory.setOnClickListener {
            val intent = Intent(this, CategoryDetails::class.java)
            startActivity(intent)
        }
    }


}