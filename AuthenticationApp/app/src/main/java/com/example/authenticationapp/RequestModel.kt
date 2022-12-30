package com.example.authenticationapp

import com.google.gson.annotations.SerializedName
import retrofit2.http.FormUrlEncoded


data class RequestModel(
    val username: String,
    val password: String
)