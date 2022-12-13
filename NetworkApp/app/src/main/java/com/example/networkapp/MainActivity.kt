package com.example.networkapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var adapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_shopping_list.layoutManager = LinearLayoutManager(this)
        getProduct()

    }

    private fun getProduct() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://my-json-server.typicode.com/mladbago/mockjson/")
            .build()
            .create(APIInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<ProductItem>?> {
            override fun onResponse(
                call: Call<List<ProductItem>?>,
                response: Response<List<ProductItem>?>
            ) {
                val responseBody = response.body()!!

                adapter = ProductAdapter(baseContext, responseBody as ArrayList<ProductItem>)
                adapter.notifyDataSetChanged()
                rv_shopping_list.adapter = adapter
            }

            override fun onFailure(call: Call<List<ProductItem>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure" + t.message)
            }
        })
    }
}