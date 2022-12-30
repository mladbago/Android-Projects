package com.example.authenticationapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface APIInterface {

    @POST("login")
    fun sendReqLogin(@Body requestModel: RequestModel) : Call<ResponseModel>
}