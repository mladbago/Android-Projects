package com.example.networkapp

import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @GET("posts")
    fun getData(): Call<List<ProductItem>>
}