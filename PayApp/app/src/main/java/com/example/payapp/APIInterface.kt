package com.example.payapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {
    @GET("pays")
    fun getData(): Call<List<PayData>>
    @POST("pays/addPay")
    fun postData(): Call<PayData>
}
