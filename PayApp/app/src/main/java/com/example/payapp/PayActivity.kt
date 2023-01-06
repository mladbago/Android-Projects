package com.example.payapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getProduct()
    }

    private fun getProduct() {
        val cardNumber = findViewById<EditText>(R.id.emailEt).text.toString()
        val cardCCV = findViewById<EditText>(R.id.passET).text.toString()
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://my-json-server.typicode.com/mladbago/mockjson/")
            .build()
            .create(APIInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<PayData>?> {
            override fun onResponse(
                call: Call<List<PayData>?>,
                response: Response<List<PayData>?>
            ) {
                val responseBody = response.body()!!
            }

            override fun onFailure(call: Call<List<PayData>?>, t: Throwable) {
                Log.d("PayActivity", "onFailure" + t.message)
            }
        })
    }
}